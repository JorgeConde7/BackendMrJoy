package com.example.demo.interceptors;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.JWTVerifier;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.codec.binary.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class BasicAuthenInterceptor extends HandlerInterceptorAdapter
{
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception 
	{
		//if (true) return true;
        String authHeader = request.getHeader("Authorization");
        //System.out.println(request.getHeaders("Authorization"));
        String urlRequest = request.getRequestURI();
        String method = request.getMethod();
        System.out.println(authHeader);
        System.out.println(urlRequest);
        System.out.println(method);

//        En caso necesitemos una ruta que no tenga autenticacion
//        Al iniciar sesion no se evaluará token
        /*if(urlRequest.equals("/registro"))
        {
            return true;
        }*/
        if (urlRequest.contains("clientes") && method.equals("GET"))
        {
        	return true;
        }

//      autentica la ruta
        if (authHeader != null) {
            try {
                System.out.println("\nBearer Token: " + authHeader);

//                obtener solo el token: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
                String token = authHeader.split(" ")[1];

//                Obtener token decodificado
                Algorithm algorithm = Algorithm.HMAC256("12345");
                JWTVerifier verifier = JWT.require(algorithm).build();

                DecodedJWT jwt = verifier.verify(token);

                System.out.println("jwt: "+jwt);

//                Obtener header y payload del token
                String headerJson = decodeToJson(jwt.getHeader());
                String payloadJson = decodeToJson(jwt.getPayload());

                System.out.println("\n"+headerJson);
                System.out.println(payloadJson);
//                Convertir json String a Map
                ObjectMapper mapper = new ObjectMapper();
                Map<String, String> map = mapper.readValue(payloadJson, Map.class);
                String user = map.get("user");

                System.out.println("\n"+map);
                System.out.println(user);

//                Aqui se debe obtener el usuario y verificar con la db si existe
                if(user.equals("Andres"))
                {
                    System.out.println("\nNo tiene autorizacion el usuario: "+ user);
                    response.sendError(401, "{\"Unauthorized\":\"No tiene permisos\"}");
                    return false;
                }
                return true;
            } 
            catch (JWTVerificationException exception) {}

        } else 
        {
            response.sendError(401, "Unauthorized");
            return false;
        }
        return super.preHandle(request, response, handler);
    }

    public String decodeToJson(final String base64) {
        return StringUtils.newStringUtf8(Base64.decodeBase64(base64));
    }
}

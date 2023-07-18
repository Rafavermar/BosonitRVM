# BosonitRVM



## Path variables y headers

Nombre proyecto Maven: block6-path-variable-headers
Tiempo estimado: 8 horas.
1º)
Programa ejemplo: https://spring.io/guides/gs/rest-service/
Hacer aplicación  como la del ejemplo. Incluyendo:
- Petición POST: mandando un objeto JSON en el body y recibiendo ese mismo objeto JSON en la respuesta (en el body).
- Petición GET: mandando parámetros en el path (http://localhost:8080/user/{id}) y devolver el mismo id mandado
- Petición PUT: mandando  Request Params (http://localhost:8080/post?var1=1&var2=2) devolver un HashMap con los datos mandados . Por ejemplo: [ {var1: 1}, {var2: 2} ]
- Petición GET: Mandar Header “h1” y “H2” a la URL http://localhost:8080/header. Cualquier otro Header deberá ser ignorado (o no mostrado al menos)
- Petición POST: Devolver todo los datos mandados: En la URL http://localhost:8080/all devolver el body si es mandado, todos los  Request Param y los headers en una objeto tipo:
  String body;
  List<String> headers;
  List<String> requestParams;

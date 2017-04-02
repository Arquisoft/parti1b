# language: es
Característica: Loguearse sin privilegios
Esquema del escenario: Login
    Dada una lista de usuarios:
      | email    | password |
      | nakamura@gmail.com   | 123456   |
      | valduvieco@gmail.com    | 123456     |
      | paco   | 123456     |
    Cuando logueo con el correo sin privilegios "nakamura@gmail.com" y la contraseña "123456"
    Entonces recibo el siguiente mensaje:

# EXERCICIO DXC
<br />

# Gerar token 

#### Para gerar o token ser autenticado no serviço de clientes, acessar o link, de preferência via postman: <br />
http://localhost:8181/oauth/token<br />

Inserir primeiro como Basic Auth:<br />
Username: dxc_client<br />
Password: dxcClient123<br />

![image](https://user-images.githubusercontent.com/29930488/214397543-3db4e722-1c60-4718-bfbf-ff586579e05a.png)

####Navegar até a aba Body, e insewrir as seguintes informações, e enviar para a geração do token:<br />
username: dxc<br />
password: dxc123<br />
grant_type: password<br />

![image](https://user-images.githubusercontent.com/29930488/214397821-941ab106-c8a5-4e06-8cfe-7a888e149d82.png)

####Token criado, agora é utilizado para as requisições, pode ser utilizado via OpenApi também, como no exemplo:<br />

URL: http://localhost:8181/swagger-ui/index.html<br />

![image](https://user-images.githubusercontent.com/29930488/214398019-845319e0-3d14-44d2-81d2-3b2e565824c8.png)

![image](https://user-images.githubusercontent.com/29930488/214398324-c0e0b690-83a2-418f-b164-cb9a0d676424.png)

![image](https://user-images.githubusercontent.com/29930488/214398362-edbab779-5c60-4b91-9a69-8faa5907f803.png)



<h1>Sistema de cadastro de PIX</h1>

<h3>Excutando o sistema</h3>
<p>Tenha o docker instalado na máquina ou siga instalação conforme <a target="_blank" href="https://www.docker.com/products/docker-desktop/">Docker</a></p>
<p>Na raiz do projeto execute o comando: <b>docker-compose up -d --build</b></p>

<h6>Alternativa de execução</h6>
<p>Faça o clone da aplicação, abra na sua IDE e execute o arquivo ItauApplication</p>

<h3>Documentação da API</h3>
<p>Swagger: http://localhost:8080/swagger-ui/index.html#/ </p>

<h3>Acompanhamento de logs</h3>
<p>Os logs deverão ser acompanhados pelo console do docker desktop</p>

<h3>Banco de dados</h3>
<p>Para facilitar a conexão e o uso externo sem deploy foi utilizado um mongodb no atlas e pode ser acessado pelo mongoDbCompass</p>
<p>String de conexão: mongodb+srv://southsystem:teste@southsystem-application.bvk8x.mongodb.net/?retryWrites=true&w=majority </p>

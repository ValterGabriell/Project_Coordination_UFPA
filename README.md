<h1 align="center">Coordenação UFPA</h1>


<!--ts-->
   * [Como usar](#como-usar)
   * [Endpoint](#endpoints)
   * [Users Endpoints](#users-endpoints)
   * [Movies Endpoints](#movies-endpoints)
   * [Credits](#credits)
<!--te-->

  
<h1>Como usar</h1>

<h2>Prerequisites</h2>
<p>Clone ou baixe o repositório e start ele através de sua IDE de preferência rodando o método main da classe principal na pasta raíz da aplicação</p>

<h1>Endpoint's Flow</h1>
<h3>BASE URL</h3>

```bash
http://localhost:8080/post
``` 

<h1>POST</h1></br>

<h2>Criar um novo post</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/</td>
    <td>cria um novo post</td>
  </tr>
</table>

<h2>Passe no corpo um Multipart com os seguintes campos:</h2></br>

```bash
body - string
title - string
link - string
image - arquivo de imagem (png, jpg, jpeg)
Authorization - token para utilização da API do Imgur

```

<hr/>

<h3>Se tudo der certo, a resposta deverá ser:</h3></br>

```bash
{
	"data": {
		"postId": "e45d5d70-4a7a-48ba-a830-475550499d1c",
		"body": "bla bla tal tal",
		"title": "evento tal",
		"postedAt": "2023-04-12",
		"link": null,
		"imgRef": "https://i.imgur.com/izj6LHK.jpg"
	},
	"message": "http://localhost:8080/post?postId=e45d5d70-4a7a-48ba-a830-475550499d1c",
	"successful": true
}
```


<h1>GET</h1></br>
<h2>Recuperando informações de um post</h2>
<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Headers</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/</td>
    <td>postId (id do post que quer recuperar)</td>
    <td>Authorization (passe o token do Imgur)</td>
    <td>tras informações do post baseado no id</td>
  </tr>
</table>



<h3>Espere isso como resposta</h3></br>

```bash
{
	"data": {
		"postId": "e45d5d70-4a7a-48ba-a830-475550499d1c",
		"body": "bla bla tal tal",
		"title": "evento tal",
		"postedAt": "2023-04-12",
		"link": null,
		"imgRef": "https://i.imgur.com/izj6LHK.jpg"
	}
```

```bash
Um erro será lançado se o post não existir
```


<h1>PUT</h1></br>


<h2>Atualizar titulo do post</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Headers</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/update-title</td>
    <td>postId (id do post que quer recuperar)</td>
    <td>Authorization (passe o token do Imgur)</td>
    <td>atualizar titulo</td>
  </tr>
</table>

<h2>Requisicao JSON</h2></br>

```bash
{
	"title":"atualizado title"
}
```

<hr/>
<h3>Resposta</h3></br>

```bash
{
	"data": "Título atualizado com sucesso!",
	"message": "http://localhost:8080/post?postId=fc4bc4a8-3ba1-4927-b61e-fba81761c6a9&postId=fc4bc4a8-3ba1-4927-b61e-fba81761c6a9",
	"successful": true
}
```


<h2>Atualizar descricao do post</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Headers</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/update-title</td>
    <td>postId (id do post que quer recuperar)</td>
    <td>Authorization (passe o token do Imgur)</td>
    <td>atualizar corpo</td>
  </tr>
</table>

<h2>Requisicao JSON</h2></br>

```bash
{
	"body":"corpo atualizado"
}
```

<hr/>
<h3>Resposta</h3></br>

```bash
{
	"data": "Descrição atualizada com sucesso!",
	"message": "http://localhost:8080/post/update-body?postId=5c1a6281-d8ed-431e-ad4b-906f53ee5248&postId=5c1a6281-d8ed-431e-ad4b-906f53ee5248",
	"successful": true
}
```


<h2>Atualizar link do post</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Headers</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/update-title</td>
    <td>postId (id do post que quer recuperar)</td>
    <td>Authorization (passe o token do Imgur)</td>
    <td>atualizar link</td>
  </tr>
</table>

<h2>Requisicao JSON</h2></br>

```bash
{
	"link":"linkatualizado.com"
}
```

<hr/>
<h3>Resposta</h3></br>

```bash
{
	"data": "Link atualizado com sucesso!",
	"message": "http://localhost:8080/post/update-link?postId=5c1a6281-d8ed-431e-ad4b-906f53ee5248&postId=5c1a6281-d8ed-431e-ad4b-906f53ee5248",
	"successful": true
}
```

<h1>DELETE</h1></br>

<h2>Deletar post por id</h2>

<table>
  <tr>
    <th>Request</th>
    <th>Query</th>
    <th>Headers</th>
    <th>Response</th>
  </tr>
  <tr>
    <td>/update-title</td>
    <td>postId (id do post que quer recuperar)</td>
    <td>Authorization (passe o token do Imgur)</td>
    <td>atualizar link</td>
  </tr>
</table>

<h3>Se tudo der certo o código 204 deverá ser retornado</h3></br>
<hr/><hr/><hr/>

<h1>Credits</h1>

---

<a href="https://www.linkedin.com/in/valter-gabriel">
  <img style="border-radius: 50%;" src="https://user-images.githubusercontent.com/63808405/171045850-84caf881-ee10-4782-9016-ea1682c4731d.jpeg" width="100px;" alt=""/>
  <br />
  <sub><b>Valter Gabriel</b></sub></a> <a href="https://www.linkedin.com/in/valter-gabriel" title="Linkedin">🚀</ a>
 
Made by Valter Gabriel 👋🏽 Get in touch!

[![Linkedin Badge](https://img.shields.io/badge/-Gabriel-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/valter-gabriel/ )](https://www.linkedin.com/in/valter-gabriel/)


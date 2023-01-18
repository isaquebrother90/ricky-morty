## Caminhos para utilizar essa aplicação

**### Sobre o backend**

**### 1- Spring Boot**

- Clone o repositório
- Importe o projeto como maven na sua IDE de preferência
- As dependências são baixadas automaticamente, verifique na sua IDE. Se preferir pode usar o comando `mvn clean install` para tal.
- Necessário ter MYSQL na sua máquina, assim como JDK, JRE
- Alguma ferramenta para teste como Postman, Insomnia e outras
- Para verificar se os testes passam, pode utilizar o comando `mvn test`

**### Docker**

- Clone o repositório
- Importar o projeto como maven na sua IDE de preferência
- Crie um Docker network:
  `docker network create app_network`
- Crie uma imagem para a aplicação:
  `docker build -t rickmorty .`
- Suba um container com a imagem na Docker network criada:
  `docker run -d -it -p 8082:8080 --network app_network --name rickmorty rickmorty`
- Suba um container com uma imagem MYSQL na Docker network criada. A imagem será baixada.
  `docker run -d -it --network app_network -e MYSQL_ROOT_PASSWORD=root -e  MYSQL_DATABASE=rickmorty --name mysql_name mysql`
* Talvez seja necessário mudar a property de datasource em application.properties, caso não localize o banco. Há uma outra opção presente lá, é só descomentar e utilizar.

**### Swagger-ui**

- Com a aplicação rodando, acesse (http://localhost:8080/swagger-ui.html) para testar os endpoints.

### Documentação em JSON

```
  "swagger": "2.0",
  "info": {
    "description": "\"REST API for searching Character of Rick and Morty API\"",
    "version": "1.0.0",
    "title": "Rick and Morty API",
    "contact": {
      "name": "Isaque Moura",
      "url": "https://www.linkedin.com/in/isaquemoura/",
      "email": "isaquebrother90@gmail.com"
    },
    "license": {
      "name": "Apache License Version 2.0",
      "url": "https://www.apache.org/licenses/LICENSE-2.0\""
    }
  },
  "host": "localhost:8080",
  "basePath": "/",
  "tags": [
    {
      "name": "ricky-morty-controller",
      "description": "Ricky Morty Controller"
    }
  ],
  "paths": {
    "/api/v1/characters": {
      "get": {
        "tags": [
          "ricky-morty-controller"
        ],
        "summary": "Get all characters",
        "operationId": "listAllCharacterUsingGET",
        "produces": [
          "*/*"
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "type": "array",
              "items": {
                "$ref": "#/definitions/CharacterClientResponseDTO"
              }
            }
          },
          "403": {
            "description": "Forbidden!"
          },
          "404": {
            "description": "Sorry! character not found!"
          },
          "500": {
            "description": "Sorry!An unknown error has occurred",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        },
        "deprecated": false
      }
    },
    "/api/v1/characters/{name}": {
      "get": {
        "tags": [
          "ricky-morty-controller"
        ],
        "summary": "Get characters by name",
        "operationId": "searchByNameUsingGET",
        "produces": [
          "*/*"
        ],
        "parameters": [
          {
            "name": "name",
            "in": "path",
            "description": "name",
            "required": true,
            "type": "string"
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "schema": {
              "$ref": "#/definitions/CharacterClientResponseDTO"
            }
          },
          "403": {
            "description": "Forbidden!"
          },
          "404": {
            "description": "Sorry! character not found!"
          },
          "500": {
            "description": "Sorry!An unknown error has occurred",
            "schema": {
              "$ref": "#/definitions/Error"
            }
          }
        },
        "deprecated": false
      }
    }
  },
  "definitions": {
    "CharacterClientResponseDTO": {
      "type": "object",
      "properties": {
        "Episodes": {
          "type": "array",
          "items": {
            "$ref": "#/definitions/EpisodeEntity"
          }
        },
        "created": {
          "type": "string"
        },
        "id": {
          "type": "integer",
          "format": "int32"
        },
        "name": {
          "type": "string"
        },
        "status": {
          "type": "string"
        },
        "url": {
          "type": "string"
        }
      },
      "title": "CharacterClientResponseDTO"
    },
    "EpisodeEntity": {
      "type": "object",
      "properties": {
        "airDate": {
          "type": "string"
        },
        "created": {
          "type": "string"
        },
        "episode": {
          "type": "string"
        },
        "id": {
          "type": "integer",
          "format": "int32"
        },
        "name": {
          "type": "string"
        },
        "url": {
          "type": "string"
        }
      },
      "title": "EpisodeEntity"
    }
  }
}
```
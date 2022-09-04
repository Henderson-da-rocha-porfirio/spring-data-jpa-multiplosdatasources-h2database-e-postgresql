# Múltiplos Databases - Trabalhando com H2
## Conexão de múltiplos Databases e Datasources
### 1. App Conectando ao mesmo tempo como:
#### - O próprio database
#### - O database H2 ( database in-memory )
### 2. São dois como exemplo, mas podem ser bem mais.
### 3. Anotações em :
#### - Entidades JPA
#### - Repositories
### 4. Configurações em:
#### - Properties
#### - DataSource
#### - EntityManagerFactory
#### - TransactionManager
#### - E tudo feito separadamente para cada Database.

### Dependência
````
<dependency>
			<groupId>com.h2database</groupId>
			<artifactId>h2</artifactId>
			<scope>runtime</scope>
</dependency>
````

### Acessando ao H2
#### 1. Verificar no console o endpoint criado:
````
h2-console
````
#### 2. No navegador colocar o endpoint criado com a porta local cofigurado no application.properties. Se não tiver sido configurada, ficará na 8080:
````
http://localhost:8080/h2-console/
````
#### 3. Quando abrir no console, colocar em JDBC URL, se der algum erro, o que saiu no console, por exemplo:
````
 H2 console available at '/h2-console'. Database available at 'jdbc:h2:mem:testdb'
````
#### 4. Testando no Postman:
````
http://localhost:8080/myAccount
````
##### - se der um erro 415: Content type 'text/plain;charset=UTF-8' not supported], colocar no Headers:
````
1. KEY: Content-Type
2. VALUE: application/json
````
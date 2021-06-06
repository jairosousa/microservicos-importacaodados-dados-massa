# Projeto Leitor-FTP

# Rodar o servidor de ftp

```
docker-compose  -f docker-server-ftp.yml up --remove-orphan
```

# Rodar o servidor S3 ninja
```
docker-compose -f docker-s3ninja up
```

* No Browser 

```http request
http://localhost:9444/
```

# Rodar o PostgresSql
```
docker-compose -f docker-postgres.yml up -d
```

* Acessar o postgres

* Acessar o container
```
docker exec -it b6713692ac07 /bin/bash

* b6713692ac07 é o número container do postgres
```

* Logar no postgres

```
root@b6713692ac07:/# psql -U postgres
```

* Criar database

```
postgres=# create database gnus;
```

* Acessar o banco gnius
```
postgres=# \c gnus
```

* verificar conteudo tabela files

```
gnus=# select * from files;
                  id                  |              path               |  status
--------------------------------------+---------------------------------+----------
 2e6b30cf-b89d-4f0d-bcfa-3430f57985ed | 20210606194805018-teste-ftp.txt | RECEBIDO
(1 row)
```

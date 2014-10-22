FROM codenvy/jdk7_postgresql
ADD $app$ /home/user/$app$
RUN unzip -q /home/user/$app$ -d /home/user
RUN sudo sed -i.bak 's/#listen_addresses/listen_addresses/g' /etc/postgresql/9.3/main/postgresql.conf
RUN sudo sed -i.bak 's/localhost/*/g' /etc/postgresql/9.3/main/postgresql.conf
RUN sudo echo "host all all 0.0.0.0/0 md5"  | sudo tee -a /etc/postgresql/9.3/main/pg_hba.conf
EXPOSE 5432
ENV CODENVY_APP_PORT_5432_HTTP 5432
ENV ARGUMENTS $args$
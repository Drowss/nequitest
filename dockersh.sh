echo "Construyendo el proyecto con Maven..."
./mvnw clean install -DskipTests

if [ $? -ne 0 ]; then
  echo "La construcción de Maven falló. Abortando."
  exit 1
fi

echo "Construyendo la imagen Docker..."
docker build -t nequi-app .

if [ $? -ne 0 ]; then
  echo "La construcción de la imagen Docker falló. Abortando."
  exit 1
fi

echo "Ejecutando el contenedor Docker..."
docker run --env-file .env -d -p 8080:8080 --name nequi-app-container nequi-app

if [ $? -eq 0 ]; then
  echo "El contenedor Docker se ejecutó exitosamente."
else
  echo "La ejecución del contenedor Docker falló."
  exit 1
fi
pwd

echo
echo "[SCRIPT] Build REST_API container as 'mono-shop:latest'"
echo

docker build -t mono-shop:latest ./REST_API

echo
echo "[SCRIPT] Build REST_Microservices/ProductService container as 'product-service:latest'"
echo

docker build -t product-service:latest ./REST_Microservices/ProductService

echo
echo "[SCRIPT] Build REST_Microservices/PaymentService container as 'payment-service:latest'"
echo

docker build -t payment-service:latest ./REST_Microservices/PaymentService

echo
echo "[SCRIPT] Build REST_Microservices/AboService container as 'abo-service:latest'"
echo

docker build -t abo-service:latest ./REST_Microservices/AboService

echo
echo "[SCRIPT] Build REST_Microservices/CartService container as 'cart-service:latest'"
echo

docker build -t cart-service:latest ./REST_Microservices/CartService

echo
echo "[SCRIPT] Build REST_Microservices/ApiGateway container as 'api-gateway:latest'"
echo

docker build -t api-gateway:latest ./REST_Microservices/ApiGateway

echo
echo "[SCRIPT] Build REST_Client_UI container as 'client-ui:latest'"
echo

docker build -t client-ui:latest ./REST_Client_UI

echo "[SCRIPT] Do you want to load the images into the minikube? (1 or 2)"
select yn in "Yes" "No"; do
    case $yn in
        Yes ) break;;
        No ) read -p "Press any key to finish ..."; exit;;
    esac
done

echo "[SCRIPT] Loading 'mono-shop:latest' into the minikube"
minikube image load mono-shop:latest
echo "[SCRIPT] Loading 'product-service:latest' into the minikube"
minikube image load product-service:latest
echo "[SCRIPT] Loading 'payment-service:latest' into the minikube"
minikube image load payment-service:latest
echo "[SCRIPT] Loading 'abo-service:latest' into the minikube"
minikube image load abo-service:latest
echo "[SCRIPT] Loading 'cart-service:latest' into the minikube"
minikube image load cart-service:latest
echo "[SCRIPT] Loading 'api-gateway:latest' into the minikube"
minikube image load api-gateway:latest
echo "[SCRIPT] Loading 'client-ui:latest' into the minikube"
minikube image load client-ui:latest

echo "[SCRIPT] Do you want to remove the local images (they will remain in the minikube)? (1 or 2)"
select yn in "Yes" "No"; do
    case $yn in
        Yes ) break;;
        No ) read -p "Press any key to finish ..."; exit;;
    esac
done

echo
echo "[SCRIPT] Removing local images"
echo

docker image remove mono-shop:latest
docker image remove product-service:latest
docker image remove payment-service:latest
docker image remove abo-service:latest
docker image remove cart-service:latest
docker image remove api-gateway:latest
docker image remove client-ui:latest

echo
read -p "[SCRIPT] Press any key to finish ..."

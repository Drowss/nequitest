## Description
It is required to build an API to manage a list of franchises. A franchise is composed of a name and a list of branches and, in turn, a branch is composed of a name and a list of products offered in the branch. A product is composed of a name and a stock quantity.

This API was built under `hexagonal architecture`, which is a software architecture that aims to create loosely coupled application components that can be easily connected to their software environment by means of `ports and adapters`. This architecture is also known as Ports and Adapters.

# Environment Variables <a name="environment-variables"></a>

The application uses environment variables for configuration. These are stored in a `.env` file at the root of the project. You should create your own `.env` file and set the following variables:

```properties
DATABASE_URL=DATABASE_URL
DATABASE_USERNAME=DATABASE_USERNAME
DATABASE_PASSWORD=DATABASE_PASSWORD
```

# How to Run


This application is containerized using Docker, and can be easily run on any system that has Docker installed.


1. **Environment Variables**: Create a `.env` file at the root of the project and set the [environment variables](#environment-variables) as described above.

2. **Install Docker**: If you haven't already, first install Docker on your machine. You can download Docker from [here](https://www.docker.com/products/docker-desktop).

3. **Run the Docker script**: Run `dockersh.sh` using bash to build the Docker images and start the containers.

The API is exposed on port `8080`

## Product Endpoints
| Endpoint                                | HTTP Method | Description                                                      | Request Body                                                                                         | Request Params                                   |
|-----------------------------------------|-------------|------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|------------------------------------------------|
| `/api/v1/product/create`                | POST        | Creates a new product                                            | `{`<br/>`"name": "myProduct",`<br/>` "stock": 12`<br/>`}`                                            |                                                  |
| `/api/v1/product/add-product/sucursal`  | POST        | Assigns a product to a branch                                    | N/A                                                                                                 | `productId`: ID of the product<br/>`sucursalId`: ID of the branch |
| `/api/v1/product/delete-product/sucursal`| DELETE      | Removes the relationship between a product and a branch          | N/A                                                                                                 | `productId`: ID of the product<br/>`branchId`: ID of the branch   |
| `/api/v1/product/update-stock`          | PATCH       | Updates the stock of a product                                   | N/A                                                                                                 | `productId`: ID of the product<br/>`stock`: New stock value       |
| `/api/v1/product/update-name`           | PATCH       | Updates the name of a product                                    | N/A                                                                                                 | `productId`: ID of the product<br/>`name`: New product name       |

## Branch Endpoints
| Endpoint                                | HTTP Method | Description                                                      | Request Body                                                                                         | Request Params                                   |
|-----------------------------------------|-------------|------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|------------------------------------------------|
| `/api/v1/branch/create`                 | POST        | Creates a new branch                                             | `{`<br/>`"name": "myBranch"`<br/>`}`                                                                 |                                                  |
| `/api/v1/branch/add-branch/franchise`   | POST        | Assigns a branch to a franchise                                  | N/A                                                                                                 | `branchId`: ID of the branch<br/>`franchiseId`: ID of the franchise |
| `/api/v1/branch/update-name`            | PATCH       | Updates the name of a branch                                    | N/A                                                                                                 | `branchId`: ID of the branch<br/>`name`: New branch name         |

## Franchise Endpoints
| Endpoint                                | HTTP Method | Description                                                      | Request Body                                                                                         | Request Params                                   |
|-----------------------------------------|-------------|------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------|------------------------------------------------|
| `/api/v1/franchise/create`              | POST        | Creates a new franchise                                          | `{`<br/>`"name": "myFranchise"`<br/>`}`                                                               |                                                  |
| `/api/v1/franchise/update-name`         | PATCH       | Updates the name of a franchise                                  | N/A                                                                                                 | `franchiseId`: ID of the franchise<br/>`name`: New franchise name |
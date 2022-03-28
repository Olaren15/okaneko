# Okaneko

A mobile app to share expenses

## Running

### Server
There are two methods to create images for the server component: with the Dockerfile or with cloud native buildpack

#### Cloud native buildpack (recommended)
 - install [pack](https://buildpacks.io/docs/tools/pack/) and [docker](https://www.docker.com/products/docker-desktop)
 - `$ pack build okaneko-server --builder heroku/buildpacks`
 - `$ docker run --rm -p 8080:8080 -e ENVIRONMENT=dev okaneko-server`

#### Dockerfile
 - install [docker](https://www.docker.com/products/docker-desktop)
 - `$ docker build -t okaneko-server .`
 - `$ docker run --rm -p 8080:8080 -e ENVIRONMENT=dev okaneko-server`

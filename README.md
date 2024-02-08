docker pull mongo

docker run -d -p 27017:27017 --name my-mongodb mongo


docker exec -it my-mongodb apt-get update
docker exec -it my-mongodb apt-get install -y mongodb-clients




# Queries:

### User
* Create a user POST [/api/v1/auth/register]()
* Edit a user PUT [/api/v1/users/edit]()
* Get User info GET [/api/v1/users/user-data]()
* Get All Users  GET [/api/v1/users/all]()
* Authenticate (logIn) POST [/api/v1/auth/authenticate]()
* Delete a user DELETE [/api/v1/users/delete]()
* LogOut POST [/api/v1/auth/logout]()


### Posts

* Create a post POST [/api/v1/posts/create]()
* Editing a post PUT [/api/v1/posts/{postId}]()
* Get a post GET [/api/v1/posts/{postId}]()
* Deleting a post DELETE [/api/v1/posts/{postId}]()


### Subscriptions

* Subscribe to User POST [/api/v1/subscribe/to/{userId}]()
* Unsubscribe from User POST [/api/v1/unsubscribe/from/{userId}]()


### Comments

* Create Comment POST [/api/v1/comments]()
* Get Comments by PostId POST [/api/v1/comments/post/{postId}]()


### Feed

* Get a user's feed (including likes and comments)
* Get another user's feed

* Leave/delete a post favorite





Likes can be left and deleted.

All processes should be described in tests.






//--------------//

//--------------//

//--------------//

Additional queries

Change account private or public  /api/v1/users/toggle-private-account

Get user info    /api/v1/users/user-data

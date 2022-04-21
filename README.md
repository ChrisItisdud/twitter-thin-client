# Twitter Thin Client

This is a (rough draft for a) thin twitter client designed to prevent users from "doomscrolling" or entering pointless discussions.

Currently, it only supports reading tweets, although post functionality is planned.

The client requires you to have access to the twitter API key and store it in a text file with the following format:

```
API KEY
$yourapikey
SECRET KEY
$yoursecretkey
BEARER TOKEN
$bearertoken
```

Currently, the client only uses the bearer token.

The client supports the following commands:

``add $username`` - adds a user to the database. $username should be the user's handle (@Itisdud, for instance) rather than their current name.

``choose $username`` - sets currently read user to the given username, provided it can be found in the database.

``get`` - lets you read through the currently read user's ten most recent tweets.

``getall`` - lets you read through all registered users' ten most recent tweets. Registered users are all users added to the database earlier.

``exit`` - closes the client.
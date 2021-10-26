db.createUser(
    {
        user : "root",
        pwd : "password",
        roles : [
            {
                role : "readWrite",
                db : "production"
            }
        ]
    }
)
db.createUser(
    {
        user : "root",
        pwd : "password",
        roles : [
            {
                role : "readWrite",
                db : "image"
            }
        ]
    }
)

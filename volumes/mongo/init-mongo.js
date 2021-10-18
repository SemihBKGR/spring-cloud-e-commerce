db.createUser(
    {
        user : "users_db_user",
        pwd : "password",
        roles : [
            {
                role : "readWrite",
                db : "users"
            }
        ]
    }
)

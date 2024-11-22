package com.forever3.finalproject.entities

class cls_users {
    var token: String = ""            // Unique identifier (e.g., Firebase UID)
    var name: String = ""             // Full name
    var email: String = ""            // Email address
    var password: String = ""         // Password (should be hashed in real scenarios)
    var profilePicture: String = ""   // URL or path to profile picture
    var role: String = ""             // Role: "Lector" or "Investigador"
    var grade: String? = null         // Academic grade (optional for Lector)
    var description: String? = null   // Bio (optional for Lector)

    // Default constructor (required for Firebase)
    constructor()

    // Constructor for all attributes
    constructor(
        token: String,
        name: String,
        email: String,
        password: String,
        profilePicture: String,
        role: String,
        grade: String? = null,
        description: String? = null
    ) {
        this.token = token
        this.name = name
        this.email = email
        this.password = password
        this.profilePicture = profilePicture
        this.role = role
        this.grade = grade
        this.description = description
    }
}

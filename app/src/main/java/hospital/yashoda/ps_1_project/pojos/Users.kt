package hospital.yashoda.ps_1_project.pojos

data class Users (
    var name: String,
    var age: String,
    var gender: String,
    var phoneNumber: String,
    var email: String,
    var state: String,
    var country: String = "India",
    var medicalReport: String,
    var prescription: String,
    var bill: String
)
// ===============================
// Registration Validation
// ===============================

function registerUser(){

    let name = document.getElementById("name").value;
    let email = document.getElementById("email").value;
    let password = document.getElementById("password").value;


    if(name=="" || email=="" || password==""){

        alert("Please fill all fields");

    }
    else{

        alert("Registration Successful!");

        window.location.href="login.html";

    }

}



// ===============================
// Login Validation
// ===============================


function loginUser(){

    let email =
    document.getElementById("loginEmail").value;


    let password =
    document.getElementById("loginPassword").value;


    if(email=="" || password==""){

        alert("Enter email and password");

    }

    else{

        alert("Login Successful");

        window.location.href="dashboard.html";

    }

}



// ===============================
// Add Vehicle
// ===============================


function addVehicle(){


let number =
document.getElementById("vehicleNumber").value;


let brand =
document.getElementById("brand").value;


let model =
document.getElementById("model").value;



if(number=="" || brand=="" || model==""){

alert("Please enter vehicle details");

}


else{


alert("Vehicle Added Successfully");


window.location.href="vehicles.html";


}


}



// ===============================
// Book Service
// ===============================


function bookService(){


let service =
document.getElementById("service").value;


let date =
document.getElementById("date").value;



if(service=="" || date==""){


alert("Select service and date");


}


else{


alert("Service Booking Successful");


window.location.href="bookingStatus.html";


}



}



// ===============================
// Admin Booking Accept
// ===============================


function acceptBooking(){


alert("Booking Accepted");


}



function rejectBooking(){


alert("Booking Rejected");


}



// ===============================
// Add Service
// ===============================


function addService(){


let name =
document.getElementById("serviceName").value;


let price =
document.getElementById("price").value;



if(name=="" || price==""){


alert("Enter service details");


}

else{


alert("New Service Added");


}


}



// ===============================
// Reminder Alert
// ===============================


function showReminder(){


alert(
"Reminder: Your vehicle service is due on 15 August 2026"
);


}


// ===============================
// Logout
// ===============================


function logout(){


alert("Logged out successfully");


window.location.href="index.html";


}
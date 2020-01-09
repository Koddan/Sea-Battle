    function checkPass() {
        if (document.getElementById('p1').value != document.getElementById('p2').value) {
        alert('Invalid confirm password')
        return false;
        } else return true;
    }

    function myFunction() {
        var x = document.getElementById("p1");
        if (x.type === "password") {
            x.type = "text";
        } else {
            x.type = "password";
        }
    }
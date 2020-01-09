    function checkPass() { 
    if (document.getElementById('p1').value != document.getElementById('p2').value) { 
        alert('Invalid confirm password') 
        return false; 
      } else return true; 
    }
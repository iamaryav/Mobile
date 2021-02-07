function openForm() {
  document.getElementById("editForm").style.display = "block";
  
}

function closeForm() {
  document.getElementById("editForm").style.display = "none";
}

window.onclick = function(event) {
    if (event.target == edit_popup) {
        edit_popup.style.display = "none";
    }
}
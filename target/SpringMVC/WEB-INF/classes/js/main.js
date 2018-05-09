function showHide(id) {
    let form = document.getElementById('department' + id);
    let label = document.getElementById('label' + id);
    let icon = document.getElementById('icon' + id);

    if (form.style.display !== "none") {
        form.style.display = "none";
        console.log(form.style.display);
        label.style.backgroundColor = "#212529";
        icon.style.transform = "rotate(0deg)";
    }
    else {
        form.style.display = "block";
        console.log("b");
        label.style.backgroundColor = "#28a745";
        icon.style.transform = "rotate(180deg)";
    }
}

function submit(id) {
    let form2 = document.getElementById('editForm' + id);
    form2.submit();
}

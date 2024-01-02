function openPopup(popupId) {
    document.getElementById(popupId).style.display = "block";
}

function closePopup(popupId) {
    document.getElementById(popupId).style.display = "none";
}

function showHiddenTable(){
    document.querySelector('.hidden').classList.remove('hidden');
    document.querySelector('.confirm').classList.add('hidden');
}
const focusedElement = document.getElementById("password");
const targetElement = document.getElementById("rule-for-password");

function showElementOnFocus() {
    focusedElement.addEventListener('focus', () => {
        targetElement.style.display = 'flex';
        targetElement.style.textAlign = 'left';
    });

    focusedElement.addEventListener('blur', () => {
        targetElement.style.display = 'none';
    });
}

showElementOnFocus();
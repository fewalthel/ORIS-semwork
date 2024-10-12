const passwordInput = document.getElementById("password");
const passwordConfirm = document.getElementById("confirm-password");
const togglePasswordButton = document.querySelector(".toggle-password span");

let inputElem = document.querySelector("input[type='password']");
let elements = [passwordInput, passwordConfirm];

function togglePasswordVisibility() {
  if (inputElem.value !== "") {
    elements.forEach((elem) => {
      if (elem && elem.type === "password") {
        elem.type = "text";
        togglePasswordButton.textContent = "hide password";
      } else {
        elem.type = "password";
        togglePasswordButton.textContent = "show password";
      }
    });
  }
}

togglePasswordButton.addEventListener("click", togglePasswordVisibility);
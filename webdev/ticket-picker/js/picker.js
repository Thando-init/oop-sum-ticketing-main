// js/picker.js
// TODO (Q7.1.1): when the user clicks one of the ticket types in the list, show
// the choice and the total in the paragraph with id "summary".
// Do not change index.html. The full rules are in README.md.

var list = document.getElementById("ticket-types");

list.onclick = function(event) {
    var target = event.target;

    // Ignore clicks that are not on an <li> element
    if (target.tagName !== "LI") {
        return;
    }

    var summary = document.getElementById("summary");
    var quantityInput = document.getElementById("quantity");
    var quantityValue = quantityInput.value.trim();
    var quantity = Number(quantityValue);

    // Verify quantity is a non-empty, whole number of at least 1
    if (quantityValue === "" || !Number.isInteger(quantity) || quantity < 1) {
        summary.textContent = "Please enter a quantity of at least 1";
        return;
    }

    var name = target.getAttribute("data-name");
    var price = Number(target.getAttribute("data-price"));
    var total = price * quantity;

    summary.textContent = "You chose " + name + " x " + quantity + ": R" + total;
};
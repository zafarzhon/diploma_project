// Функция для установки cookie
function setCookie(name, value, days) {
    const d = new Date();
    d.setTime(d.getTime() + (days * 24 * 60 * 60 * 1000)); // Устанавливаем время жизни cookie
    const expires = "expires=" + d.toUTCString();
    document.cookie = name + "=" + JSON.stringify(value) + ";" + expires + ";path=/;Secure;SameSite=Strict";
}


// Функция для получения cookie
function getCookie(name) {
    const nameEq = name + "=";
    const ca = document.cookie.split(';');
    for (let i = 0; i < ca.length; i++) {
        let c = ca[i];
        while (c.charAt(0) == ' ') c = c.substring(1, c.length); // Убираем пробелы в начале
        if (c.indexOf(nameEq) == 0) return JSON.parse(c.substring(nameEq.length, c.length));
    }
}


// Функция для обновления корзины в cookie
function updateCart(productId, quantity) {
    let cart = getCookie('cart') || {}; // Получаем текущую корзину или создаём пустую

    // Обновляем количество товара в корзине
    if (cart[productId]) {
        cart[productId] += quantity; // Увеличиваем количество на указанное
        if (cart[productId] <= 0) {
            delete cart[productId]; // Удаляем товар, если количество стало <= 0
        }
    } else if (quantity > 0) {
        cart[productId] = quantity; // Если товара нет в корзине, добавляем его
    }

    // Сохраняем обновлённую корзину в cookie
    setCookie('cart', cart, 7); // Храним корзину в cookies на 7 дней

    // Обновляем бейдж корзины
    updateCartBadge();
}

// Обработчик для кнопки "В корзину"
document.querySelectorAll(".add-to-cart-btn").forEach(button => {
    button.addEventListener("click", function () {
        const productId = this.dataset.id;

        // Обновляем корзину, увеличиваем количество на 1
        updateCart(productId, 1);

        // Скрываем кнопку "В корзину" и показываем счетчик
        const addToCartContainer = document.getElementById(`add-to-cart-container-${productId}`);
        const quantityContainer = document.getElementById(`quantity-container-${productId}`);

        addToCartContainer.classList.add("d-none"); // Скрываем кнопку
        quantityContainer.classList.remove("d-none"); // Показываем счетчик

        // Инициализируем количество на основе куки
        const quantitySpan = document.getElementById(`quantity-${productId}`);
        quantitySpan.innerText = getCookie('cart')[productId];
    });
});

// Обработчик для кнопок " + " и " - "
document.querySelectorAll(".increment-btn").forEach(button => {
    button.addEventListener("click", function () {
        const productId = this.dataset.id;
        const stock = parseInt(this.dataset.stock); // Доступное количество товара
        const quantitySpan = document.getElementById(`quantity-${productId}`);
        let quantity = parseInt(quantitySpan.innerText);

        if (quantity < stock) {
            quantity++;
            quantitySpan.innerText = quantity; // Обновляем отображаемое количество

            // Обновляем корзину в cookie
            updateCart(productId, 1); // Увеличиваем на 1
        }
    });
});

document.querySelectorAll(".decrement-btn").forEach(button => {
    button.addEventListener("click", function () {
        const productId = this.dataset.id;
        const quantitySpan = document.getElementById(`quantity-${productId}`);
        let quantity = parseInt(quantitySpan.innerText);

        if (quantity > 0) {
            quantity--;
            quantitySpan.innerText = quantity; // Обновляем отображаемое количество

            // Обновляем корзину в cookie
            updateCart(productId, -1); // Уменьшаем на 1

            // Если количество стало 0, показываем кнопку "В корзину"
            if (quantity === 0) {
                const addToCartContainer = document.getElementById(`add-to-cart-container-${productId}`);
                const quantityContainer = document.getElementById(`quantity-container-${productId}`);
                addToCartContainer.classList.remove("d-none"); // Показываем кнопку
                quantityContainer.classList.add("d-none"); // Скрываем счетчик
            }
        }
    });
});


// Функция для удаления товара из корзины
function removeProductFromCart(productId) {
    const cart = getCookie('cart') || {}; // Получаем корзину из куки
    if (cart[productId]) {
        delete cart[productId]; // Удаляем товар из корзины
    }

    // Сохраняем обновлённую корзину в cookie
    setCookie('cart', cart, 7);

    // Перерисовываем корзину
    renderCart();
    updateCartBadge(); // Обновляем бейдж
    updatePageAfterProduct(); // Обновляем отображение на странице
}


// Функция для обновления бейджа
function updateCartBadge() {
    const cart = getCookie('cart') || {}; // Получаем корзину из куки
    const badge = document.querySelector('.btn-cart .badge'); // Находим бейдж
    const totalTypes = Object.keys(cart).length; // Считаем количество типов товаров (ключей)
    badge.textContent = totalTypes; // Обновляем текст бейджа
}


// Обработчик кнопки "Корзина"
document.querySelector('.btn-cart').addEventListener('click', function () {
    renderCart(); // Рендерим корзину при каждом открытии модального окна
});


// Функция для обновления отображения на странице после изменения корзины
function updatePageAfterProduct() {
    const cart = getCookie('cart') || {}; // Получаем корзину из куки
    // Для каждого товара из корзины обновляем количество на странице
    console.log(cart)
    for (const productId in cart) {
        const quantity = cart[productId];
        const quantitySpan = document.getElementById(`quantity-${productId}`);
        const addToCartContainer = document.getElementById(`add-to-cart-container-${productId}`);
        const quantityContainer = document.getElementById(`quantity-container-${productId}`);
        if (quantitySpan) {
            quantitySpan.innerText = quantity;
            if (quantity > 0) {
                // Если количество больше 0, скрываем кнопку "В корзину" и показываем контролы количества
                addToCartContainer.classList.add("d-none");
                quantityContainer.classList.remove("d-none");
            }
        }
    }
}

function removeCartHelpFunc(id) {
    const addToCartContainer = document.getElementById(`add-to-cart-container-${id}`);
    const quantityContainer = document.getElementById(`quantity-container-${id}`);
    addToCartContainer.classList.remove("d-none");
    quantityContainer.classList.add("d-none");
}


// Вызов функции при загрузке страницы
window.onload = function () {
    updatePageAfterProduct();
    updateCartBadge(); // Обновляем бейдж при загрузке страницы

    // Дополнительно, если корзина динамически обновляется
    document.querySelectorAll('.add-to-cart-btn, .increment-btn, .decrement-btn').forEach(button => {
        button.addEventListener('click', updateCartBadge); // Обновляем бейдж при изменении корзины
    });
};


function renderCart() {
    const cart = getCookie('cart') || {}; // Получаем корзину из куки
    const cartItemsContainer = document.getElementById('cartItemsContainer');
    cartItemsContainer.innerHTML = ''; // Очищаем старое содержимое

    if (Object.keys(cart).length === 0) {
        cartItemsContainer.innerHTML = '<p>Корзина пуста</p>';
        return;
    }

    // Генерируем HTML для каждого товара
    for (const productId in cart) {
        const quantity = cart[productId];
        if (quantity > 0) {
            const productHtml = `
            <div class="d-flex justify-content-between align-items-center mb-3 border-bottom pb-2">
                <div>
                    <a class="fs-6" href="/products/${productId}">
                    <div><img src="https://via.placeholder.com/150" alt=""></div></a>
                    <p>Количество: <span>${quantity}</span></p>
                </div>
                <div>
                    <button class="btn btn-sm btn-outline-secondary decrement-cart-item" data-id="${productId}">-</button>
                    <button class="btn btn-sm btn-outline-secondary increment-cart-item" data-id="${productId}">+</button>
                </div>
            </div>
        `;
            cartItemsContainer.innerHTML += productHtml;
        }
    }

    // Добавляем обработчики для кнопок + и -
    document.querySelectorAll('.decrement-cart-item').forEach(button => {
        button.addEventListener('click', function () {
            updateCart(this.dataset.id, -1);
            renderCart();
            removeCartHelpFunc(this.dataset.id);
            updatePageAfterProduct();
        });
    });

    document.querySelectorAll('.increment-cart-item').forEach(button => {
        button.addEventListener('click', function () {
            updateCart(this.dataset.id, 1);
            renderCart();
            removeCartHelpFunc(this.dataset.id);
            updatePageAfterProduct();
        });
    });

    // Добавляем обработчики для кнопок "Удалить"
    document.querySelectorAll('.remove-from-cart-btn').forEach(button => {
        button.addEventListener('click', function () {
            const productId = this.dataset.id;
            removeCartHelpFunc(productId);
            removeProductFromCart(productId); // Удаляем товар
        });
    });
}
'use strict';

const select = document.querySelector('#selectList'),
    selectedBook = document.querySelector('#selectedBook'),
    button = document.querySelector('#regQueue');

//Створення та рендер елементів списку
booksData.forEach(el => {
    const selectOption = document.createElement('option');

    selectOption.setAttribute('id', el.id);
    selectOption.textContent = el.name;
    selectOption.value = el.name;

    select.append(selectOption);
});

//Зміна книжки за обраним пунктом(стандартним методом switch)

// select.addEventListener('change', function() {
//     switch(true) {
//         case(this.value === 'Ікабог'): selectedBook.src = "./img/books/Ickagod.jpeg";
//         break;

//         case(this.value === 'Завтрашній день кішки'): selectedBook.src = "./img/books/Cat's tomorrow day.jpeg";
//         break;

//         case(this.value === "П'ять частин апельсину"): selectedBook.src = "./img/books/Five pieces of orange.jpeg";
//         break;

//         case(this.value === "Гормони щастя"): selectedBook.src = "./img/books/Hormones of happiness.png";
//         break;

//         case(this.value === "Хребти божевілля"): selectedBook.src = "./img/books/Ridges of madness.jfif";
//         break;

//         case(this.value === "Дім на краю ночі"): selectedBook.src = "./img/books/The house on the edge of night.jpg";
//         break;

//         case(this.value === "Молитва про Оуена Міні"): selectedBook.src = "./img/books/The prayer about Owen Mini.jpg";
//         break;

//         case(this.value === "Проблеми надзвичайно багатих азіатів"): selectedBook.src = "./img/books/The problems of insanly rich asians.jpg";
//         break;

//         case(this.value === "Прованс назавжди"): selectedBook.src = "./img/books/the provance forever.jpg";
//         break;

//         case(this.value === "Ловці неба"): selectedBook.src = "./img/books/Sky chasers.jpeg";
//         break;
//     };
// });

//Зміна книжки за обраним пунктом(оптимальне рішення)
select.addEventListener('change', function() {
    renderChangedBook(this.value);
});

function renderChangedBook(val) {
    booksData.forEach(el => {
        if (val === el.name) {
            selectedBook.src = el.poster;
        } else {
            return;
        };
    });
};

//Власна валідація імені користувача у формі
function validateName(value) {
    let inputName = document.querySelector('#inputName'),
        avail = false;

    value = value.replace(/\s+/g, '');

    switch (true) {
        case (!value.trim() === true):
            inputName.value = "Ім'я не може бути порожнім";

            break;

        case (value.length < 2):
            inputName.value = "Ім'я занадто коротке!";

            break;

        case (/[~`!?@_"'#$№;:.,%^&*/()+=|{}[<>\]-]/g.test(value) === true):
            inputName.value = "Ім'я не може мати ніяких знаків!";

            break;

        case (/[0-9]/g.test(value) === true):
            inputName.value = "Ім'я не може мати ніяких цифр!";
            
            break;

        default:
            avail = true;

            break;
        };

    return avail;
};

//Регулярний вираз для валідації пошти користувача
function emailRegexp(value) {
    const regexp = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    
    return regexp.test(value);
};

//Валідація пошти користувача
function validateEmail(value) {
    let avail = false;

    value = value.replace(/\s+/g, '');

    if (emailRegexp(value) === false) {
        document.querySelector('#inputEmail').value = 'Невірний формат пошти!';
    }

    else {
        avail = true;
    };

    return avail;
};

//Відправка даних користувача для електронної черги
function sendUserData() {
    const data = new FormData(document.querySelector('#form')),
        request = new XMLHttpRequest();

    request.open('POST', '../php/main.php');

    request.send(data);

    request.addEventListener('load', () => {
        if (request.readyState === 4 && request.status === 200) {
            alert("Ви успішно встали у чергу. Очікуйте на зворотній зв'язок від нашого оператору.");

            localStorage.setItem("clientPhoneNumber", document.querySelector('#inputNumber').value);

            document.querySelector('#form').reset();

            selectedBook.src = booksData[0].poster;

            console.log(request.readyState, request.statusText);
        }

        else {
            alert("Помилка. Будь-ласка перезагрузіть сторінку та спробуйте ще раз.");
        };
    });

    request.addEventListener('error', () => {
        console.log(request.readyState, request.statusText);
    });
};

//Перевірка усіх полей форми на вірність та номеру телефона користувача на унікальність, яка дозволяє перевірити, чи вставав користувач у чергу раніше чи ні.
document.querySelector('#form').addEventListener('submit', function(e) {
    e.preventDefault();

    validateName(document.querySelector('#inputName').value);
    validateEmail(document.querySelector('#inputEmail').value);

    if (validateName(document.querySelector('#inputName').value) === true && validateEmail(document.querySelector('#inputEmail').value)=== true) {
        if (document.querySelector('#inputNumber').value === localStorage.getItem("clientPhoneNumber")) {
            alert('На даний момент ви вже стоїте у черзі за книжкою.');
            document.querySelector('#form').reset();
        } else {
            sendUserData();
        };

    } else {
        return;
    };
});
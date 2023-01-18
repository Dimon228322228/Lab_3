function displayCanvas() {
    let canvasHTML = document.getElementById('myCanvas');
    let contextHTML = canvasHTML.getContext('2d');
    contextHTML.strokeRect(0, 0, canvasHTML.width, canvasHTML.height);

    //Расчет координат центра и радиуса часов
    let radiusClock = canvasHTML.width / 2 - 10;
    let xCenterClock = canvasHTML.width / 2;
    let yCenterClock = canvasHTML.height / 2;

    //Очистка экрана.
    contextHTML.fillStyle = "#F5FFFA"; //цвет экрана ebf3eb
    contextHTML.fillRect(0, 0, canvasHTML.width, canvasHTML.height);

    //Рисуем контур часов
    contextHTML.strokeStyle = "#000000";
    contextHTML.lineWidth = 1;  //толщина линии
    contextHTML.beginPath();
    contextHTML.arc(xCenterClock, yCenterClock, radiusClock, 0, 2 * Math.PI, true);
    contextHTML.moveTo(xCenterClock, yCenterClock);
    contextHTML.stroke();
    contextHTML.closePath();

    //Рисуем рисочки часов
    let radiusNum = radiusClock - 10; //Радиус расположения рисочек
    let radiusPoint;
    //contextHTML.strokeStyle = "#6633cc";

    for (let tm = 0; tm < 60; tm++) {
        contextHTML.beginPath();
        if (tm % 5 == 0) { radiusPoint = 5; } else { radiusPoint = 2; } //для выделения часовых рисочек
        let xPointM = xCenterClock + radiusNum * Math.cos(-6 * tm * (Math.PI / 180) + Math.PI / 2);
        let yPointM = yCenterClock - radiusNum * Math.sin(-6 * tm * (Math.PI / 180) + Math.PI / 2);
        contextHTML.arc(xPointM, yPointM, radiusPoint, 0, 2 * Math.PI, true);
        contextHTML.stroke();
        contextHTML.closePath();
    }

    //Оцифровка циферблата часов
    for (let th = 1; th <= 12; th++) {
        contextHTML.beginPath();
        contextHTML.font = 'bold 22px sans-serif ';     //цифры
        contextHTML.strokeStyle = "#3C3901";         //цвет цифр

        let xText = xCenterClock + (radiusNum - 30) * Math.cos(-30 * th * (Math.PI / 180) + Math.PI / 2);
        let yText = yCenterClock - (radiusNum - 30) * Math.sin(-30 * th * (Math.PI / 180) + Math.PI / 2);
        if (th <= 9) {
            contextHTML.strokeText(th, xText - 5, yText + 10);
        } else {
            contextHTML.strokeText(th, xText - 15, yText + 10);
        }
        contextHTML.stroke();
        contextHTML.closePath();
    }

    //Рисуем стрелки
    let lengthSeconds = radiusNum - 10;
    let lengthMinutes = radiusNum - 15;
    let lengthHour = lengthMinutes / 1.5;
    let d = new Date();                //Получаем экземпляр даты
    let t_sec = 6 * d.getSeconds();                           //Определяем угол для секунд
    let t_min = 6 * (d.getMinutes() + (1 / 60) * d.getSeconds()); //Определяем угол для минут
    let t_hour = 30 * (d.getHours() + (1 / 60) * d.getMinutes()); //Определяем угол для часов

    //Рисуем секунды
    contextHTML.beginPath();
    contextHTML.strokeStyle = "#cc0000"; //Секундная стрелка
    contextHTML.moveTo(xCenterClock, yCenterClock);
    contextHTML.lineTo(xCenterClock + lengthSeconds * Math.cos(Math.PI / 2 - t_sec * (Math.PI / 180)),
        yCenterClock - lengthSeconds * Math.sin(Math.PI / 2 - t_sec * (Math.PI / 180)));
    contextHTML.stroke();
    contextHTML.closePath();

    //Рисуем минуты
    contextHTML.beginPath();
    contextHTML.strokeStyle = "#000000"; //Стрелки
    contextHTML.lineWidth = 3; //толщина минутной стрелки
    contextHTML.moveTo(xCenterClock, yCenterClock);
    contextHTML.lineTo(xCenterClock + lengthMinutes * Math.cos(Math.PI / 2 - t_min * (Math.PI / 180)),
        yCenterClock - lengthMinutes * Math.sin(Math.PI / 2 - t_min * (Math.PI / 180)));
    contextHTML.stroke();
    contextHTML.closePath();

    //Рисуем часы
    contextHTML.beginPath();
    contextHTML.lineWidth = 5; // толщина маленикой стрелки
    //contextHTML.strokeStyle =  "#cc0000"; //Цвет стрелки
    contextHTML.moveTo(xCenterClock, yCenterClock);
    contextHTML.lineTo(xCenterClock + lengthHour * Math.cos(Math.PI / 2 - t_hour * (Math.PI / 180)),
        yCenterClock - lengthHour * Math.sin(Math.PI / 2 - t_hour * (Math.PI / 180)));
    contextHTML.stroke();
    contextHTML.closePath();

    //Рисуем центр часов
    contextHTML.beginPath();
    contextHTML.strokeStyle = "#000000";    // рамка-круг
    contextHTML.fillStyle = "#FCE804";      // цвет круга
    contextHTML.lineWidth = 3;              //толщина круга
    contextHTML.arc(xCenterClock, yCenterClock, 5, 0, 2 * Math.PI, true);
    contextHTML.stroke();
    contextHTML.fill();
    contextHTML.closePath();

    return;
}

window.setInterval( displayCanvas, 5000);
## TestVKProject

Тестовое приложение, отображающее вывод товаров из сети.

<b>В приложении реализовано:</b>
1. Список товаров с полями title, description, thumbnail (т.к. описание у всех товаров отличается по кол-ву строк, вывод title и description реализовала через TruncateAt)
2. Пагинация при скролле
3. Дополнительно реализовала: экран детальной информации с полным описанием товара и строимостью, поиск по названию товара

<table>
  <tr>
    <td><b>Поиск по названию товара</b></td>
    <td><b>Поиск, если товар не найден</b></td>
  </tr>
  <tr>
    <td><img src="https://github.com/avbolotova/TestVKProject/blob/master/app/src/main/res/drawable/search.gif" width="300"></td>
    <td><img src="https://github.com/avbolotova/TestVKProject/blob/master/app/src/main/res/drawable/search_error.gif" width="300"></td>
  </tr>
</table>

<table>
  <tr>
    <td><b>Реализация экрана детальной <br>информации</b></td>
    <td><b>Реализация экрана, в случае <br>отсутствия сети или ошибки</b></td>
  </tr>
  <tr>
    <td><img src="https://github.com/avbolotova/TestVKProject/blob/master/app/src/main/res/drawable/details.gif" width="300"></td>
    <td><img src="https://github.com/avbolotova/TestVKProject/blob/master/app/src/main/res/drawable/screenshot_error.jpg" width="300"></td>
  </tr>
</table>

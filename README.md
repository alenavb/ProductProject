## TestVKProject

Приложение, отображающее вывод товаров из сети.

<b>В приложении реализовано:</b>
1. Список товаров с полями title, description, thumbnail (т.к. описание у всех товаров отличается по кол-ву строк, вывод title и description реализовала через TruncateAt)
2. Пагинация при скролле
3. Поиск по названию товара (в случае, если товар не найден - показывается окно, что ничего не найдено)
4. Дополнительно реализовала: экран детальной информации с полным описанием товара и стоимостью, поиск по названию товара
5. Дополнительно реализовала инъекцию зависимости Dagger 2

<table>
  <tr>
    <td><b>Реализация экрана поиска</b></td>
    <td><b>Реализация экрана поиска,<br>если ничего не найдено</b></td>
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

# <h1 align="center">project-hibernate-2</h1>

## Предложения по улучшению:

1. Staff ссылается на Store (store_id), Store ссылается на Staff (manager_staff_id) - убрать из Store ссылку на manager_staff_id, вместо этого добавить в Staff столбец is_manager типа boolean.

2. Хранение поля store_id в Customer кажется излишним, если это поле обновляется каждый раз после покупки, то лучше отслеживать последний посещенный магазин по последней аренде

3. Payment хранит ссылки на rental_id, customer_id и staff_id, и Rental хранит ссылки на customer_id и staff_id, поэтому поле customer_id в Payment точно излишне, а staff_id является излишним в зависимости от ситуации - возможно ли оформление аренды и оплаты у разного персонала в одном магазине, или предполагается, что этим всегда будет заниматься один и тот же персонал (или как вариант убрать staff_id из Rental, оставить только ссылки на customer_id и inventory_id). 

4. Нужен ли вообще film_text, если он полностью дублирует поля из film

5. special_features vs film больше подходит связь Many-To-Many, вынести в отдельную таблицу
select
    a.author_id,
    a.author_name,
    b.category,
    SUM(b.price * s.sales) as total_sales
    # b.book_id,
    # b.price * sum(sales)
from BOOK b
join BOOK_SALES s on b.book_id = s.book_id and s.sales_date like '2022-01%'
join AUTHOR a on b.author_id = a.author_id

group by a.author_id, b.category
order by a.author_id, b.category desc
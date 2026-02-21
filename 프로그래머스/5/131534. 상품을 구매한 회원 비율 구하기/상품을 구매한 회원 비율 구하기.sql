select
    YEAR(sale.SALES_DATE) as year,
    MONTH(sale.SALES_DATE) as month,
    count(distinct sale.user_id) as PURCHASED_USERS,
    ROUND(count(distinct sale.user_id) / (select count(*) from USER_INFO where YEAR(JOINED) = 2021), 1) as PUCHASED_RATIO
from USER_INFO info
join ONLINE_SALE sale on info.user_id = sale.user_id
where YEAR(JOINED) = 2021
group by YEAR(sale.SALES_DATE), MONTH(sale.SALES_DATE)
order by YEAR(sale.SALES_DATE), MONTH(sale.SALES_DATE)
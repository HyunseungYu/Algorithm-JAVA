select
    count(*) AS USERS
from USER_INFO
where AGE between 20 and 29
    and DATE_FORMAT(JOINED, "%Y") = 2021
select
    PT_NAME,
    PT_NO,
    GEND_CD,
    AGE,
    IFNULL(TLNO, "NONE") AS TNLO
from patient
where age <= 12 and GEND_CD = 'W'
order by age desc, pt_name
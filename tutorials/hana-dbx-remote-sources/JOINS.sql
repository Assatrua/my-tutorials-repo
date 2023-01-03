--1.CREAT TABLES
create table #t1
(a int)

create table #t2
(b int)

-- 2.INSERT VALUES
insert #t1
values
(1), (2), (3),(null),(5);

insert #t2
values
(1), (2), (4),(null),(5);

--3. JOIN
SELECT A,B
FROM #t1
JOIN #t2 ON A=B

--4.LEFT JOIN
SELECT A,B
FROM #t1
LEFT JOIN #t2 ON A=B

--5. RIGHT JOIN
SELECT A,B
FROM #t1
RIGHT JOIN #t2 ON A=B


--6. FULL OUTER JOIN
SELECT A,B
FROM #t1
FULL OUTER JOIN #t2 ON A=B
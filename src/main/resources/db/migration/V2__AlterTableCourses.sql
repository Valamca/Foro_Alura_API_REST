ALTER TABLE courses ADD activo tinyint;
UPDATE courses set activo = 1;
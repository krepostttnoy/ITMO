INSERT INTO
    location (name, weather, clean_air)
VALUES
    ('Лес чудес', 'Солнечно, ожидается дождь', 8.5),
    ('Тайланд, Пхукет', 'Море, жара', 7.2),
    (
        'Горные вершины',
        'Холодно, ветренно, стремно',
        6.7
    );

INSERT INTO
    purpose (
        location_id,
        name,
        degree_of_competition,
        impact_on_plans
    )
VALUES
    (1, 'Охота', 7.5, 5.0),
    (2, 'Товарный обмен', 6.0, 4.5),
    (3, 'Исследование', 8.2, 7.0);

INSERT INTO
    next_purpose (location_id, name, difficulty, significance)
VALUES
    (1, 'Выживание среди кабанов', 9.0, 8.0),
    (2, 'Курить напас', 7.0, 6.5),
    (3, 'Кража снега', 8.5, 9.0);

INSERT INTO
    character(
        location_id,
        purpose_id,
        next_purpose_id,
        name,
        purpose,
        lvl_of_satisf
    )
VALUES
    (
        1,
        1,
        1,
        'Лара Крофт',
        'Выживание и охота. Дружба с кабанами',
        7.2
    ),
    (
        2,
        2,
        2,
        'Марк Анатольевич',
        'МОЯ ЛЕТЕТЬ В ТАЙЛАНД',
        9.8
    ),
    (
        3,
        3,
        3,
        'Петр Гном',
        'Воровская жизнь нелегекая...',
        8.0
    );

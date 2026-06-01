INSERT INTO market_role (id, role)
VALUES (1, 'USER'), (0, 'ADMIN')
ON CONFLICT (id) DO NOTHING;

INSERT INTO market_user (id, username, password, register_date, is_banned, is_enabled)
VALUES (0, 'admin', '$2a$10$5zINaSQ2.MpQnEi0uPNbGOf2SvomNwCUiRZcHZZMfPDZoULq/yoR.', CURRENT_TIMESTAMP, false, true)
ON CONFLICT (id) DO NOTHING;

INSERT INTO market_user_roles (roles_id, user_id)
VALUES (0, 0), (1, 0)
ON CONFLICT DO NOTHING;

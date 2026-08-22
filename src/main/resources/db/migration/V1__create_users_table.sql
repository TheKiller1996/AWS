CREATE TABLE auth_users (
    id           UUID PRIMARY KEY,
    email        VARCHAR(255) NOT NULL,
    password     VARCHAR(255) NOT NULL,
    created_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    updated_at   TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),
    modified_at  TIMESTAMP WITH TIME ZONE NOT NULL DEFAULT now(),

    CONSTRAINT uk_auth_users_email UNIQUE (email)
);
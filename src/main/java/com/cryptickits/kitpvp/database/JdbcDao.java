package com.cryptickits.kitpvp.database;

import com.cryptickits.kitpvp.service.ServiceExecutor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by Matthew E on 6/14/2017.
 */
public abstract class JdbcDao< V> {
    private String tableName;
    private Connection connection;

    public JdbcDao(String tableName) {
        this.tableName = tableName;
        try {
            this.connection = ConnectionFactory.getInstance().getConnection(this.tableName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getTableName() {
        return tableName;
    }

    public Connection getConnection() {
        return connection;
    }

    public abstract V create(V v, boolean async);

    public abstract void delete(V v, boolean async);

    public abstract V get(String key, boolean async) throws SQLException;

    public abstract void save(V v, boolean async);

    public int executeUpdate(String sql, boolean async, Object... objects) {
        if (async) {
            ServiceExecutor.newAsyncExecutor().submit(() -> {
                PreparedStatement preparedStatement = null;
                try {
                    preparedStatement = this.connection.prepareStatement(sql);
                    if (objects != null) {
                        for (int i = 0; i < objects.length; i++) {
                            Object object = objects[i];
                            if (object instanceof String) {
                                preparedStatement.setString((i + 1), String.valueOf(object));
                            } else if (object instanceof Double) {
                                preparedStatement.setDouble((i + 1), (Double) object);
                            } else if (object instanceof Long) {
                                preparedStatement.setLong((i + 1), (Long) object);
                            } else if (object instanceof Integer) {
                                preparedStatement.setInt((i + 1), (Integer) object);
                            } else {
                                preparedStatement.setObject((i + 1), object);
                            }
                        }
                    }
                    return preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return -1;
            });
        } else {
            PreparedStatement preparedStatement = null;
            try {
                preparedStatement = this.connection.prepareStatement(sql);
                if (objects != null) {
                    for (int i = 0; i < objects.length; i++) {
                        Object object = objects[i];
                        if (object instanceof String) {
                            preparedStatement.setString((i + 1), String.valueOf(object));
                        } else if (object instanceof Double) {
                            preparedStatement.setDouble((i + 1), (Double) object);
                        } else if (object instanceof Long) {
                            preparedStatement.setLong((i + 1), (Long) object);
                        } else if (object instanceof Integer) {
                            preparedStatement.setInt((i + 1), (Integer) object);
                        } else {
                            preparedStatement.setObject((i + 1), object);
                        }
                    }
                }
                return preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    public ResultSet executeQuery(String sql, boolean async, Object... objects) {
        if (async) {
            ServiceExecutor.newAsyncExecutor().submit(() -> {
                try {
                    PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
                    if (objects != null) {
                        for (int i = 0; i < objects.length; i++) {
                            Object object = objects[i];
                            if (object instanceof String) {
                                preparedStatement.setString((i + 1), String.valueOf(object));
                            } else if (object instanceof Double) {
                                preparedStatement.setDouble((i + 1), (Double) object);
                            } else if (object instanceof Long) {
                                preparedStatement.setLong((i + 1), (Long) object);
                            } else if (object instanceof Integer) {
                                preparedStatement.setInt((i + 1), (Integer) object);
                            } else {
                                preparedStatement.setObject((i + 1), object);
                            }
                        }
                    }
                    return preparedStatement.executeQuery();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return null;
            });
        } else {
            try {
                PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
                if (objects != null) {
                    for (int i = 0; i < objects.length; i++) {
                        Object object = objects[i];
                        if (object instanceof String) {
                            preparedStatement.setString((i + 1), String.valueOf(object));
                        } else if (object instanceof Double) {
                            preparedStatement.setDouble((i + 1), (Double) object);
                        } else if (object instanceof Long) {
                            preparedStatement.setLong((i + 1), (Long) object);
                        } else if (object instanceof Integer) {
                            preparedStatement.setInt((i + 1), (Integer) object);
                        } else {
                            preparedStatement.setObject((i + 1), object);
                        }
                    }
                }
                return preparedStatement.executeQuery();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}

package io.sandstorm.common.query.domain;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

/**
 * Sort option for queries. You have to provide at least a list of properties to sort by that must not include
 * {@literal null} or empty strings.
 */
public class Sort implements Iterable<Sort.Order> {

    public static final Direction DEFAULT_DIRECTION = Direction.ASC;

    private final List<Order> orders;

    /**
     * @param orders must not be {@literal null}.
     */
    public Sort(Order... orders) {
        this(Arrays.asList(orders));
    }

    /**
     * @param orders must not be {@literal null} or contain {@literal null}.
     */
    public Sort(List<Order> orders) {
        if (null == orders || orders.isEmpty()) {
            throw new IllegalArgumentException("Have to provide at least one sort property to sort by!");
        }
        this.orders = orders;
    }

    /**
     * The sort directions for given properties default to ASC.
     *
     * @param properties must not be {@literal null} or contain {@literal null} or empty strings
     */
    public Sort(String... properties) {
        this(DEFAULT_DIRECTION, properties);
    }

    /**
     * @param direction defaults to {@link Sort#DEFAULT_DIRECTION} (for {@literal null} cases, too)
     * @param properties must not be {@literal null}, empty or contain {@literal null} or empty strings.
     */
    public Sort(Direction direction, String... properties) {
        this(direction, properties == null ? new ArrayList<String>() : Arrays.asList(properties));
    }

    /**
     * @param direction defaults to {@link Sort#DEFAULT_DIRECTION} (for {@literal null} cases, too)
     * @param properties must not be {@literal null} or contain {@literal null} or empty strings.
     */
    public Sort(Direction direction, List<String> properties) {
        if (properties == null || properties.isEmpty()) {
            throw new IllegalArgumentException("Have to provide at least one property to sort by!");
        }
        this.orders = new ArrayList<>(properties.size());
        for (String property : properties) {
            this.orders.add(new Order(direction, property));
        }
    }

    /**
     * Returns the order registered for the given property.
     */
    public Optional<Order> orderFor(String property) {
        for (Order order : this) {
            if (order.getProperty().equals(property)) {
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    @Override
    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Sort)) {
            return false;
        }
        Sort that = (Sort) obj;
        return this.orders.equals(that.orders);
    }

    @Override
    public int hashCode() {
        int result = 17;
        result = 31 * result + orders.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return StringUtils.collectionToCommaDelimitedString(orders);
    }

    /**
     * PropertyPath implements the pairing of an {@link Direction} and a property.
     */
    public static class Order {

        private static final boolean DEFAULT_IGNORE_CASE = false;
        
        private final Direction direction;
        private final String property;
        private final boolean ignoreCase;
        private final NullHandling nullHandling;

        /**
         * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property must not be {@literal null} or empty.
         */
        public Order(Direction direction, String property) {
            this(direction, property, DEFAULT_IGNORE_CASE, null);
        }

        /**
         * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property must not be {@literal null} or empty.
         * @param nullHandling can be {@literal null}, will default to {@link NullHandling#NATIVE}.
         */
        public Order(Direction direction, String property, NullHandling nullHandling) {
            this(direction, property, DEFAULT_IGNORE_CASE, nullHandling);
        }

        /**
         * The sort direction for given property will defaults to {@link Sort#DEFAULT_DIRECTION}.
         *
         * @param property must not be {@literal null} or empty.
         */
        public Order(String property) {
            this(DEFAULT_DIRECTION, property);
        }

        /**
         * If given direction is {@literal null} then direction defaults to {@link Sort#DEFAULT_DIRECTION}.
         *
         * @param direction can be {@literal null}, will default to {@link Sort#DEFAULT_DIRECTION}
         * @param property must not be {@literal null} or empty.
         * @param ignoreCase true if sorting should be case insensitive, false if sorting should be case sensitive.
         * @param nullHandling can be {@literal null}, will default to {@link NullHandling#NATIVE}.
         */
        private Order(Direction direction, String property, boolean ignoreCase, NullHandling nullHandling) {
            if (!StringUtils.hasText(property)) {
                throw new IllegalArgumentException("Property must not null or empty!");
            }

            this.direction = direction == null ? DEFAULT_DIRECTION : direction;
            this.property = property;
            this.ignoreCase = ignoreCase;
            this.nullHandling = nullHandling == null ? NullHandling.NATIVE : nullHandling;
        }

        public Direction getDirection() {
            return direction;
        }

        public String getProperty() {
            return property;
        }

        public boolean isAscending() {
            return this.direction.isAscending();
        }

        public boolean isDescending() {
            return this.direction.isDescending();
        }

        public boolean isIgnoreCase() {
            return ignoreCase;
        }

        /**
         * Returns the {@link NullHandling} hint
         */
        public NullHandling getNullHandling() {
            return nullHandling;
        }

        /**
         * Returns a new {@link Order} with the given {@link Direction}.
         */
        public Order with(Direction direction) {
            return new Order(direction, this.property, this.ignoreCase, this.nullHandling);
        }

        /**
         * Returns a new {@link Order} with the given property.
         *
         * @param property must not be {@literal null} or empty.
         */
        public Order withProperty(String property) {
            return new Order(this.direction, property, this.ignoreCase, this.nullHandling);
        }

        /**
         * Returns a new {@link Order} with case insensitive sorting enabled.
         */
        public Order ignoreCase() {
            return new Order(direction, property, true, nullHandling);
        }

        /**
         * Returns a {@link Order} with the given {@link NullHandling}.
         *
         * @param nullHandling can be {@literal null}.
         */
        public Order with(NullHandling nullHandling) {
            return new Order(direction, this.property, ignoreCase, nullHandling);
        }

        /**
         * Returns a {@link Order} with {@link NullHandling#NULLS_FIRST} as null handling hint.
         */
        public Order nullsFirst() {
            return with(NullHandling.NULLS_FIRST);
        }

        /**
         * Returns a {@link Order} with {@link NullHandling#NULLS_LAST} as null handling hint.
         */
        public Order nullsLast() {
            return with(NullHandling.NULLS_LAST);
        }

        /**
         * Returns a {@link Order} with {@link NullHandling#NATIVE} as null handling hint.
         */
        public Order nullsNative() {
            return with(NullHandling.NATIVE);
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = 31 * result + direction.hashCode();
            result = 31 * result + property.hashCode();
            result = 31 * result + (ignoreCase ? 1 : 0);
            result = 31 * result + nullHandling.hashCode();
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Order)) {
                return false;
            }
            Order that = (Order) obj;
            return this.direction.equals(that.direction) && this.property.equals(that.property)
                    && this.ignoreCase == that.ignoreCase && this.nullHandling.equals(that.nullHandling);
        }

        @Override
        public String toString() {
            String result = String.format("%s: %s", property, direction);
            if (!NullHandling.NATIVE.equals(nullHandling)) {
                result += ", " + nullHandling;
            }
            if (ignoreCase) {
                result += ", ignoring case";
            }
            return result;
        }
    }

    /**
     * Enumeration for sort directions.
     */
    public enum Direction {

        ASC, DESC;

        /**
         * Returns whether the direction is ascending.
         */
        public boolean isAscending() {
            return this.equals(ASC);
        }

        /**
         * Returns whether the direction is descending.
         */
        public boolean isDescending() {
            return this.equals(DESC);
        }

        /**
         * Returns the {@link Direction} enum for the given {@link String} value.
         *
         * @param value
         * @throws IllegalArgumentException in case the given value cannot be parsed into an enum value.
         * @return
         */
        public static Direction fromString(String value) {

            try {
                return Direction.valueOf(value.toUpperCase(Locale.US));
            } catch (Exception e) {
                throw new IllegalArgumentException(String.format(
                        "Invalid value '%s' for order direction! Has to be either 'desc' or 'asc' (case insensitive).", value), e);
            }
        }
    }

    /**
     * Enumeration for null handling hints that can be used in {@link Order} expressions.
     */
    public enum NullHandling {

        /**
         * Lets the data store decide what to do with nulls.
         */
        NATIVE,

        /**
         * A hint to the used data store to order entries with null values before non null entries.
         */
        NULLS_FIRST,

        /**
         * A hint to the used data store to order entries with null values after non null entries.
         */
        NULLS_LAST
    }

}

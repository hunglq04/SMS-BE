package com.sms.be.repository.impl;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import com.sms.be.constant.OrderStatus;
import com.sms.be.model.Customer;
import com.sms.be.model.Order;
import com.sms.be.model.QOrder;
import com.sms.be.repository.base.AbstractCustomQuery;
import com.sms.be.repository.custom.OrderRepositoryCustom;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.querydsl.core.group.GroupBy.groupBy;

public class OrderRepositoryImpl extends AbstractCustomQuery implements OrderRepositoryCustom {

    @Override
    public List<Order> findOrderHistoryByCustomer(Customer customer) {
        return new JPAQuery<>(entityManager).from(QOrder.order)
                .where(QOrder.order.customer.eq(customer))
                .select(QOrder.order)
                .fetch();
    }

    @Override
    public Page<Order> getOrderPageByDate(int pageSize, int pageOffset, String fromDate) {
        BooleanBuilder condition = new BooleanBuilder();
        LocalDateTime date = LocalDateTime.parse(fromDate);
        condition.and(QOrder.order.dateTime.gt(date))
                .or((QOrder.order.dateTime.eq(date)));
        JPQLQuery<Order> query = new JPAQuery<>(entityManager)
                .from(QOrder.order)
                .where(condition)
                .orderBy(QOrder.order.dateTime.asc(),
                        QOrder.order.status.asc())
                .select(QOrder.order);
        return getPage(query, pageOffset, pageSize);
    }
    
    @Override
    public Long getRevenueFromProducts(String date, String monthYear, Integer year) {
        return new JPAQuery<>(entityManager).from(order)
                .where(order.status.eq(OrderStatus.COMPLETED))
                .where(buildDateCondition(date, monthYear, year, order.dateTime))
                .select(order.total).fetch().stream()
                .reduce(Long::sum).orElse(0L);
    }

    @Override
    public Long countOrders(String date, String monthYear, Integer year, OrderStatus orderStatus) {
        return new JPAQuery<>(entityManager).from(order).where(order.status.eq(orderStatus))
                .where(buildDateCondition(date, monthYear, year, order.dateTime))
                .fetchCount();
    }

    @Override
    public Map<Integer, Long> groupRevenueFromProductsByDate(Long salonId, String date, String monthYear,
            Integer year) {
        NumberExpression<Integer> dateGroup = year != null ? order.dateTime.yearMonth() : order.dateTime.dayOfMonth();
        Map<Integer, Long> result = new JPAQuery<>(entityManager).from(order)
                .where(buildDateConditionForChart(date, monthYear, year, order.dateTime))
                .where(order.status.eq(OrderStatus.COMPLETED))
                .groupBy(dateGroup).select(dateGroup, order.total.count())
                .transform(groupBy(dateGroup).as(order.total.sum()));
        return StringUtils.isBlank(date) ? result :
                result.entrySet().stream().collect(Collectors
                        .toMap(e -> Year.of(LocalDate.parse(date).getYear()).atMonth(LocalDate.parse(date).getMonth())
                                .atDay(e.getKey()).getDayOfWeek().getValue(), Map.Entry::getValue));
    }

    @Override
    public Map<String, Integer> groupTopProductByDate(Long salonId, String date, String monthYear, Integer year) {
        return new JPAQuery<>(entityManager).from(orderDetail)
                .where(orderDetail.order.status.eq(OrderStatus.COMPLETED))
                .where(buildDateConditionForChart(date, monthYear, year, orderDetail.order.dateTime))
                .groupBy(orderDetail.product.name)
                .select(orderDetail.product.name, orderDetail.quantity.sum())
                .orderBy(orderDetail.quantity.sum().desc())
                .transform(groupBy(orderDetail.product.name).as(orderDetail.quantity.sum()));
    }

}

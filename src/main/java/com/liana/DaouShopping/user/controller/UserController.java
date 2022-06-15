package com.liana.DaouShopping.user.controller;

import com.liana.DaouShopping.global.exception.CustomErrorResponse;
import com.liana.DaouShopping.global.exception.IdNotFoundException;
import com.liana.DaouShopping.item.model.Option;
import com.liana.DaouShopping.payments.model.Coupon;
import com.liana.DaouShopping.user.controller.repository.UserCouponRepository;
import com.liana.DaouShopping.user.controller.repository.UserRepository;
import com.liana.DaouShopping.user.model.User;
import com.liana.DaouShopping.user.model.UserCoupon;
import com.liana.DaouShopping.user.model.UserCouponInfo;
import com.liana.DaouShopping.user.model.UserInfo;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserCouponRepository userCouponRepository;

    // get coupons of user by id
    @GetMapping("/{id}/coupons")
    @ApiOperation(value = "Get Coupon By Id / 사용자 쿠폰 조회"
            , notes = "Get all usable coupons user have. Sort coupons by amount." +
            "\n사용자가 가진 모든 사용가능한 쿠폰을 조회합니다. 금액 순으로 쿠폰을 정렬합니다.")
    public ResponseEntity<List<UserCouponInfo>> getCouponById(@PathVariable String id,
                                                              @RequestParam(value="price", required = false, defaultValue = "0") double price) {
        List<UserCouponInfo> userCouponInfoList = userCouponRepository.findById(id);
        if (userCouponInfoList.size() > 0 && price > 0){
            double min = Double.MAX_VALUE;
            long couponId = -1;
            for (UserCouponInfo u: userCouponInfoList) {
                // 최소 금액 만족하지 않으면 확인 안함
                if (u.getCMin()>price)
                    continue;
                // %쿠폰인지, 금액 할인쿠폰인지에 따라 분기
                if (u.isUsePercent()){
                    double salePrice = price * (u.getCPercent()/100.0);
                    if (salePrice > u.getCPercentLimit()){
                        u.setCalValue(u.getCPercentLimit());
                    }else{
                        u.setCalValue(salePrice);
                    }
                }else{
                    u.setCalValue(u.getCPrice());
                }
            }
            // 할인받는 금액 큰 순으로 정렬
            Comparator<UserCouponInfo> comparator = new Comparator<UserCouponInfo>() {
                @Override
                public int compare(UserCouponInfo o1, UserCouponInfo o2) {
                    return (int)o2.getCalValue() - (int)o1.getCalValue();
                }
            };
            Collections.sort(userCouponInfoList, comparator);
        }
            return ResponseEntity.ok(userCouponInfoList);
    }

    //user login
    @PostMapping
    @ApiOperation(value = "Get User Information / 사용자 정보 조회"
            , notes = "If user inputs correct ID & PW, return information." +
            "\n 사용자가 ID, PW를 맞게 입력하면 사용자 정보를 반환합니다.")
    public ResponseEntity<?> login(@RequestBody User user)  {
        User getUser = userRepository.findById(user.getUserId());
        if(getUser==null) {
            throw new IdNotFoundException("회원정보를");
        }else {
            if (getUser.getUserPw().equals(user.getUserPw())){
                // 유저정보 반환
                UserInfo loginUser = UserInfo.builder()
                        .userId(getUser.getUserId())
                        .userName(getUser.getUserName())
                        .address1(getUser.getAddress1())
                        .address2(getUser.getAddress2())
                        .point(getUser.getPoint())
                        .build();
                return ResponseEntity.ok(loginUser);
            }else{
                CustomErrorResponse errors = new CustomErrorResponse();
                errors.setTimestamp(LocalDateTime.now());
                errors.setError("비밀번호가 틀립니다.");
                errors.setStatus(HttpStatus.NOT_FOUND.value());
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(errors);
            }
        }

    }

}

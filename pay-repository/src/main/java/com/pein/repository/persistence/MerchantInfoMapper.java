package com.pein.repository.persistence;

import com.pein.repository.entity.MerchantInfo;

public interface MerchantInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    int insert(MerchantInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    int insertSelective(MerchantInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    MerchantInfo selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(MerchantInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table center_merchant_info
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(MerchantInfo record);
}
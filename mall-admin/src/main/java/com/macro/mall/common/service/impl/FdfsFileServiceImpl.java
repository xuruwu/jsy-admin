package com.macro.mall.common.service.impl;

import com.macro.mall.common.dto.FdfsFileDto;
import com.macro.mall.common.mapper.FdfsFilenameMapperMapper;
import com.macro.mall.common.model.FdfsFilenameMapper;
import com.macro.mall.common.model.FdfsFilenameMapperExample;
import com.macro.mall.common.service.FdfsFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 会员标签库管理Service实现类
 * Created by ruiwu.xu on 2019/01/08.
 */
@Service
public class FdfsFileServiceImpl implements FdfsFileService {
    @Autowired
    private FdfsFilenameMapperMapper fdfsFilenameMapperMapper;
    /**
     * 增加文件映射关系
     *
     * @param fdfsFilenameMapper
     * @return
     */
    @Override
    public int add(FdfsFilenameMapper  fdfsFilenameMapper) {
        return fdfsFilenameMapperMapper.insert(fdfsFilenameMapper);
    }

    /**
     * @param fdfsFileDto
     * @return
     */
    @Override
    public FdfsFilenameMapper search(FdfsFileDto fdfsFileDto) {
        FdfsFilenameMapperExample example = new FdfsFilenameMapperExample();
        example.createCriteria().andFdfsFullPathNameEqualTo(fdfsFileDto.getFdfsFullName());
        return fdfsFilenameMapperMapper.selectByExample(example).get(0);
    }
}

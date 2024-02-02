// 2009-2013 als

// 定义时间范围
var startDate = '2009-01-01';
var endDate = '2013-12-31';

// 定义经纬度
var point = ee.Geometry.Point([101.0122, 24.5381]);


// 定义一个函数来提取每张图像在指定点的LAI值和对应的时间
var getTimeSeries = function(image) {
  // 使用reduceRegion获取指定点的LAI值
  var value = image.reduceRegion({
    reducer: ee.Reducer.first(), // 使用first() reducer获取格点的值
    geometry: point,
    scale: 500 // 设置scale以匹配MODIS数据的空间分辨率
  }).get('Lai_500m');

  // 获取图像的时间戳
  var time = image.date().format('YYYY-MM-dd');
  
  // 返回一个包含时间和LAI值的字典
  return ee.Feature(null, {'time': time, 'LAI': value});
};

var laiCollection = ee.ImageCollection('MODIS/006/MOD15A2H')
                      .filterDate(startDate, endDate)
                      .select('Lai_500m');

// 应用函数到图像集合中的每张图像并转换为FeatureCollection
var timeSeries = laiCollection.map(getTimeSeries);

// 导出时间序列为CSV文件
Export.table.toDrive({
  collection: timeSeries,
  description: 'LAI_Time_Series', // 为导出任务和文件指定一个描述性名称
  folder: 'GEE_Folder', // 指定Google Drive中的文件夹名称，如不存在将自动创建
  fileNamePrefix: 'LAI_2019_2013_als', // 导出文件的前缀
  fileFormat: 'CSV', // 指定文件格式为CSV
  selectors: ['time', 'LAI'] // 指定要导出的属性列，这里是时间和LAI值
});

/////////


// 2009-2013 xsbn

// 定义时间范围
var startDate = '2003-01-01';
var endDate = '2015-12-31';

// 定义经纬度
var point = ee.Geometry.Point([101.25, 21.9167]);

// 定义一个函数来提取每张图像在指定点的LAI值和对应的时间
var getTimeSeries = function(image) {
  // 使用reduceRegion获取指定点的LAI值
  var value = image.reduceRegion({
    reducer: ee.Reducer.first(), // 使用first() reducer获取格点的值
    geometry: point,
    scale: 500 // 设置scale以匹配MODIS数据的空间分辨率
  }).get('Lai_500m');

  // 获取图像的时间戳
  var time = image.date().format('YYYY-MM-dd');
  
  // 返回一个包含时间和LAI值的字典
  return ee.Feature(null, {'time': time, 'LAI': value});
};

var laiCollection = ee.ImageCollection('MODIS/006/MOD15A2H')
                      .filterDate(startDate, endDate)
                      .select('Lai_500m');

// 应用函数到图像集合中的每张图像并转换为FeatureCollection
var timeSeries = laiCollection.map(getTimeSeries);

// 导出时间序列为CSV文件
Export.table.toDrive({
  collection: timeSeries,
  description: 'LAI_Time_Series', // 为导出任务和文件指定一个描述性名称
  folder: 'GEE_Folder', // 指定Google Drive中的文件夹名称，如不存在将自动创建
  fileNamePrefix: 'LAI_2003_2015_xsbn', // 导出文件的前缀
  fileFormat: 'CSV', // 指定文件格式为CSV
  selectors: ['time', 'LAI'] // 指定要导出的属性列，这里是时间和LAI值
});

/////////


// 2009-2013 yj

// 定义时间范围
var startDate = '2013-01-01';
var endDate = '2015-12-31';

// 定义经纬度
var point = ee.Geometry.Point([103.1775, 23.4739]);

// 定义一个函数来提取每张图像在指定点的LAI值和对应的时间
var getTimeSeries = function(image) {
  // 使用reduceRegion获取指定点的LAI值
  var value = image.reduceRegion({
    reducer: ee.Reducer.first(), // 使用first() reducer获取格点的值
    geometry: point,
    scale: 500 // 设置scale以匹配MODIS数据的空间分辨率
  }).get('Lai_500m');

  // 获取图像的时间戳
  var time = image.date().format('YYYY-MM-dd');
  
  // 返回一个包含时间和LAI值的字典
  return ee.Feature(null, {'time': time, 'LAI': value});
};

var laiCollection = ee.ImageCollection('MODIS/006/MOD15A2H')
                      .filterDate(startDate, endDate)
                      .select('Lai_500m');

// 应用函数到图像集合中的每张图像并转换为FeatureCollection
var timeSeries = laiCollection.map(getTimeSeries);

// 导出时间序列为CSV文件
Export.table.toDrive({
  collection: timeSeries,
  description: 'LAI_Time_Series', // 为导出任务和文件指定一个描述性名称
  folder: 'GEE_Folder', // 指定Google Drive中的文件夹名称，如不存在将自动创建
  fileNamePrefix: 'LAI_2013_2015_yj', // 导出文件的前缀
  fileFormat: 'CSV', // 指定文件格式为CSV
  selectors: ['time', 'LAI'] // 指定要导出的属性列，这里是时间和LAI值
});



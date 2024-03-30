package dao;

import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Projections;
import com.mongodb.client.model.Sorts;
import com.mongodb.client.model.Updates;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import com.mongodb.reactivestreams.client.AggregatePublisher;
import com.mongodb.reactivestreams.client.MongoCollection;
import com.mongodb.reactivestreams.client.MongoDatabase;

import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

import org.bson.codecs.configuration.CodecProvider;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;

import entities.Zips;

public class ZipsDao {

	private MongoCollection<Zips> zipCollection;

	public ZipsDao(MongoDatabase db) {
	

		CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();

		CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));

		zipCollection = db.getCollection("Zips", Zips.class).withCodecRegistry(pojoCodecRegistry);
	}

	public List<Zips> findAll(String state) {
	    List<Zips> result = new ArrayList<>();
	    CountDownLatch latch = new CountDownLatch(1);

	    Publisher<Zips> pub = zipCollection.find(Filters.eq("state", state));

	    Subscriber<Zips> sub = new Subscriber<Zips>() {
	        @Override
	        public void onSubscribe(Subscription s) {
	            s.request(Long.MAX_VALUE); // Yêu cầu tất cả các bản ghi
	        }

	        @Override
	        public void onNext(Zips t) {
	            result.add(t);
	            System.out.println(t);
	        }

	        @Override
	        public void onError(Throwable t) {
	            // Xử lý lỗi
	            t.printStackTrace();
	            latch.countDown();
	        }

	        @Override
	        public void onComplete() {
	            latch.countDown();
	        }
	    };

	    pub.subscribe(sub);

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public List<Zips> findAllByStateAndCity(String state, String city) {
	    List<Zips> result = new ArrayList<>();
	    CountDownLatch latch = new CountDownLatch(1);

	    Publisher<Zips> pub = zipCollection.find(Filters.and(
	    		Filters.eq("state", state),
	    		Filters.eq("city", city)
	    		));

	    Subscriber<Zips> sub = new Subscriber<Zips>() {
	        @Override
	        public void onSubscribe(Subscription s) {
	            s.request(Long.MAX_VALUE); // Yêu cầu tất cả các bản ghi
	        }

	        @Override
	        public void onNext(Zips t) {
	            result.add(t);
	            System.out.println(t);
	        }

	        @Override
	        public void onError(Throwable t) {
	            // Xử lý lỗi
	            t.printStackTrace();
	            latch.countDown();
	        }

	        @Override
	        public void onComplete() {
	            latch.countDown();
	        }
	    };

	    pub.subscribe(sub);

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public List<Zips> findInRange(int minValue , int maxValue) {
	    List<Zips> result = new ArrayList<>();
	    CountDownLatch latch = new CountDownLatch(1);

	    Publisher<Zips> pub = zipCollection.find(Filters.and(
	    		Filters.gte("pop", minValue),
	    		Filters.lte("pop", maxValue)
	    		));

	    Subscriber<Zips> sub = new Subscriber<Zips>() {
	        @Override
	        public void onSubscribe(Subscription s) {
	            s.request(Long.MAX_VALUE); // Yêu cầu tất cả các bản ghi
	        }

	        @Override
	        public void onNext(Zips t) {
	            result.add(t);
	            System.out.println(t);
	        }

	        @Override
	        public void onError(Throwable t) {
	            // Xử lý lỗi
	            t.printStackTrace();
	            latch.countDown();
	        }

	        @Override
	        public void onComplete() {
	            latch.countDown();
	        }
	    };

	    pub.subscribe(sub);

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	public List<Zips> findInRangeAndMaxValue(int minValue , int maxValue) {
	    List<Zips> result = new ArrayList<>();
	    CountDownLatch latch = new CountDownLatch(1);

	    Publisher<Zips> pub = zipCollection.find(Filters.and(
	    		Filters.gte("pop", minValue),
	    		Filters.lte("pop", maxValue)
	    		))
	    		//.projection(Projections.include("zip")) // Chỉ chọn trường zip
	    		.sort(Sorts.descending("zip")) //Sắp xếp theo thứ tự giảm dần, ascending là tăng dần
	    		.limit(1);
	    Subscriber<Zips> sub = new Subscriber<Zips>() {
	        @Override
	        public void onSubscribe(Subscription s) {
	            s.request(Long.MAX_VALUE); // Yêu cầu tất cả các bản ghi
	        }

	        @Override
	        public void onNext(Zips t) {
	            result.add(t);
	            System.out.println(t);
	        }

	        @Override
	        public void onError(Throwable t) {
	            // Xử lý lỗi
	            t.printStackTrace();
	            latch.countDown();
	        }

	        @Override
	        public void onComplete() {
	            latch.countDown();
	        }
	    };

	    pub.subscribe(sub);

	    try {
	        latch.await();
	    } catch (InterruptedException e) {
	        e.printStackTrace();
	    }

	    return result;
	}
	
	
	public boolean addZip(Zips zips) {
		AtomicBoolean result = new AtomicBoolean(false);

		CountDownLatch latch = new CountDownLatch(1);
		
		Publisher<InsertOneResult> pub = zipCollection.insertOne(zips);
		
		Subscriber<InsertOneResult> sub = new Subscriber<InsertOneResult>() {

			@Override
			public void onSubscribe(Subscription s) {
				s.request(1);
			}

			@Override
			public void onNext(InsertOneResult t) {
				System.out.println("address inserted: " + t.getInsertedId());
				
				result.set(t.getInsertedId() != null ? true : false);
			}

			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

			@Override
			public void onComplete() {
				System.out.println("Completed");
				latch.countDown();
			}

		};

		pub.subscribe(sub);
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return result.get();
	}

//	public void updateCityByState(String state, String newCity) {
//	    CountDownLatch latch = new CountDownLatch(1);
//
//	    // Tạo một filter để chỉ định điều kiện cần cập nhật
//	    Bson filter = Filters.eq("state", state);
//
//	    // Tạo một update để cập nhật trường "city"
//	    Bson update = Updates.set("city", newCity);
//
//	    // Thực hiện cập nhật
//	    UpdateResult result = zipCollection.updateMany(filter, update);
//
//	    // In số lượng bản ghi đã được cập nhật ra console
//	    System.out.println(result.getModifiedCount() + " documents updated");
//
//	    latch.countDown();
//	}
	
//	public boolean addProduct(Product product) {
//		AtomicBoolean result = new AtomicBoolean(false);
//
//		CountDownLatch latch = new CountDownLatch(1);
//		
//		Publisher<InsertOneResult> pub = productCollection.insertOne(product);
//		
//		Subscriber<InsertOneResult> sub = new Subscriber<InsertOneResult>() {
//
//			@Override
//			public void onSubscribe(Subscription s) {
//				s.request(1);
//			}
//
//			@Override
//			public void onNext(InsertOneResult t) {
//				System.out.println("Product inserted: " + t.getInsertedId());
//				
//				result.set(t.getInsertedId() != null ? true : false);
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				t.printStackTrace();
//			}
//
//			@Override
//			public void onComplete() {
//				System.out.println("Completed");
//				latch.countDown();
//			}
//
//		};
//
//		pub.subscribe(sub);
//		try {
//			latch.await();
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//
//		return result.get();
//	}
//
//	public List<Product> getProductMaxPrice() throws InterruptedException {
//
//		CountDownLatch latch = new CountDownLatch(1);
//		List<Product> products = new ArrayList<Product>();
//		AggregatePublisher<Product> pub = productCollection
//				.aggregate(Arrays.asList(
//						Aggregates.group(null, Accumulators.max("maxPrice", "$price"),
//								Accumulators.addToSet("ps", "$$ROOT")),
//						Aggregates.unwind("$ps"),
//						Aggregates.match(Filters.expr(Filters.eq("$eq", Arrays.asList("$ps.price", "$maxPrice")))),
//						Aggregates.replaceWith("$ps")
//
//				));
//
//		Subscriber<Product> sub = new Subscriber<Product>() {
//
//			private Subscription s;
//			
//			@Override
//			public void onSubscribe(Subscription s) {
//				this.s = s;
//				this.s.request(1);
//			}
//
//			@Override
//			public void onNext(Product t) {
//				products.add(t);
//				this.s.request(1);
//			}
//
//			@Override
//			public void onError(Throwable t) {
//				t.printStackTrace();
//			}
//
//			@Override
//			public void onComplete() {
//				latch.countDown();
//			}
//		};
//		
//		pub.subscribe(sub);
//		
//		latch.await();
//		
//		return products;
//	}

//	
//	public Product getProduct(long id) {
////		Document doc = new Document("_id", id);
////		return productCollection.find(doc).first();
//		
//		return productCollection.find(Filters.eq("_id", id)).first();
//	}
//	
//	public boolean addProduct(Product product) {
//		InsertOneResult result = (InsertOneResult) productCollection.insertOne(product);
//		
//		return result.getInsertedId() != null ? true : false;
//	}
//	
//	public boolean addProducts(List<Product> products) {
//		InsertManyResult result = productCollection.insertMany(products);
//		
//		return result.getInsertedIds() != null ? true : false;
//	}
//	
//	// db.products.aggregate([{$group:{_id:'$model_year', num:{$sum:1}}}])
//	
//	public List<Product> getProductList() {
//		List<Product> products = new ArrayList<Product>();
//		
//		//db.products.find({product_name:{$regex:'^Prod*',$options:'i'}})
////		Document doc = new Document("product_name", new Document("$regex", "^Prod*").append("$options", "i"));
//		
//		productCollection
////		.find(doc)
//		.find(Filters.regex("product_name", "^Prod*", "i"))
//		.iterator()
//		.forEachRemaining(products::add);
//		
//		return products;
//	}
//	
//	public Product getProduct(long id) {
////		Document doc = new Document("_id", id);
////		return productCollection.find(doc).first();
//		
//		return productCollection.find(Filters.eq("_id", id)).first();
//	}
//	
	public boolean updateProduct(String city, String state) {
		UpdateResult result =  (UpdateResult) zipCollection
				.updateOne(Filters.eq("state", state),Updates.set("city", city));
		
		return result.getModifiedCount() > 0 ? true : false;
	}

	
	public boolean updateProduct1(String newCity, String state) {
	    AtomicBoolean modified = new AtomicBoolean(false);
	    
	    zipCollection
	        .updateOne(Filters.eq("state", state), Updates.set("city", newCity));
	    
	    return modified.get();
	}

//	
//	/**
//	 *  db.products.aggregate([
//	 *  	{ $group: { _id: null, ps: { $addToSet: '$$ROOT' }, maxPrice: { $max: '$price' } } }, 
//	 *  	{ $unwind: '$ps' }, 
//	 *  	{ $match: { $expr: { $eq: ['$ps.price', '$maxPrice'] } } }, 
//	 *  	{$replaceWith: '$ps'}
//	 *  ] )
//	 *  
//	 */
//	
//	public List<Product> getProductMaxPrice() {
//						
//		List<Product> products = new ArrayList<Product>();
//		AggregateIterable<Product> result = productCollection
//				.aggregate(Arrays.asList(
//						Aggregates.group(null, Accumulators.max("maxPrice", "$price"), Accumulators.addToSet("ps",  "$$ROOT")),
//						Aggregates.unwind("$ps"),	
//						Aggregates.match(Filters.expr(Filters.eq("$eq",Arrays.asList("$ps.price", "$maxPrice")))),
//						Aggregates.replaceWith("$ps")
//						
//						));
//		
//		System.out.println(Arrays.asList(
//				Aggregates.group(null, Accumulators.max("maxPrice", "$price"), Accumulators.addToSet("ps",  "$$ROOT")),
//				Aggregates.unwind("$ps"),	
//				Aggregates.match(Filters.expr(Filters.eq("$eq",Arrays.asList("$ps.price", "$maxPrice")))),
//				Aggregates.replaceWith("$ps")	
//				));
//		
//		result.iterator()
//		.forEachRemaining(p -> System.out.println(p));
//		
//		return products;
//	}
//	
//	/**
//	 *  db.products.aggregate([
//	 *  	{ $group: { _id: null, ps: { $addToSet: '$$ROOT' }, maxPrice: { $max: '$price' } } }, 
//	 *  	{ $unwind: '$ps' }, 
//	 *  	{ $match: { $expr: { $eq: ['$ps.price', '$maxPrice'] } } }, 
//	 *  	{ $replaceWith: '$ps' }
//	 *  ] )
//	 *  
//	 */
//	public List<Product> getProductMaxPriceUsingDocumentParse() {
//		List<Product> products = new ArrayList<Product>();
//		
//		Document group = Document.parse("{ $group: { _id: null, ps: { $addToSet: '$$ROOT' }, maxPrice: { $max: '$price' } } }");
//		Document unwind = Document.parse("{ $unwind: '$ps' }");
//		Document match = Document.parse("{ $match: { $expr: { $eq: ['$ps.price', '$maxPrice'] } } }");
//		Document replaceWith = Document.parse("{ $replaceWith: '$ps' }");
//		
//		AggregateIterable<Product> result = productCollection
//				.aggregate(Arrays.asList(group, unwind, match, replaceWith));
//		
//		result.iterator()
//		.forEachRemaining(p -> System.out.println(p));
//		
//		return products;
//	}
}

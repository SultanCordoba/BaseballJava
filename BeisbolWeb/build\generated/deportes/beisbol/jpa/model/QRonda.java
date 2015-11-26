package deportes.beisbol.jpa.model;

import static com.mysema.query.types.PathMetadataFactory.*;

import com.mysema.query.types.path.*;

import com.mysema.query.types.PathMetadata;
import javax.annotation.Generated;
import com.mysema.query.types.Path;


/**
 * QRonda is a Querydsl query type for Ronda
 */
@Generated("com.mysema.query.codegen.EntitySerializer")
public class QRonda extends EntityPathBase<Ronda> {

    private static final long serialVersionUID = 512131612L;

    public static final QRonda ronda = new QRonda("ronda");

    public final DateTimePath<java.sql.Timestamp> fechaActualizacion = createDateTime("fechaActualizacion", java.sql.Timestamp.class);

    public final NumberPath<Byte> id = createNumber("id", Byte.class);

    public final StringPath nombre = createString("nombre");

    public QRonda(String variable) {
        super(Ronda.class, forVariable(variable));
    }

    public QRonda(Path<? extends Ronda> path) {
        super(path.getType(), path.getMetadata());
    }

    public QRonda(PathMetadata<?> metadata) {
        super(Ronda.class, metadata);
    }

}


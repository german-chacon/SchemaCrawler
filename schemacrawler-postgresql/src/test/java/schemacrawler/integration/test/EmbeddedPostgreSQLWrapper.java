/*
========================================================================
SchemaCrawler
http://www.schemacrawler.com
Copyright (c) 2000-2019, Sualeh Fatehi <sualeh@hotmail.com>.
All rights reserved.
------------------------------------------------------------------------

SchemaCrawler is distributed in the hope that it will be useful, but
WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.

SchemaCrawler and the accompanying materials are made available under
the terms of the Eclipse Public License v1.0, GNU General Public License
v3 or GNU Lesser General Public License v3.

You may elect to redistribute this code under any of these licenses.

The Eclipse Public License is available at:
http://www.eclipse.org/legal/epl-v10.html

The GNU General Public License v3 and the GNU Lesser General Public
License v3 are available at:
http://www.gnu.org/licenses/

========================================================================
*/

package schemacrawler.integration.test;


import java.util.logging.Level;

import org.testcontainers.containers.PostgreSQLContainer;
import schemacrawler.schemacrawler.SchemaCrawlerException;
import schemacrawler.test.utility.DatabaseServerContainer;
import sf.util.SchemaCrawlerLogger;

public class EmbeddedPostgreSQLWrapper
  implements DatabaseServerContainer
{

  private static final SchemaCrawlerLogger LOGGER = SchemaCrawlerLogger
    .getLogger(EmbeddedPostgreSQLWrapper.class.getName());

  private PostgreSQLContainer dbContainer;

  public String getConnectionUrl()
  {
    return dbContainer.getJdbcUrl();
  }

  public void startServer()
    throws SchemaCrawlerException
  {
    try
    {
      dbContainer = new PostgreSQLContainer<>();
      LOGGER.log(Level.FINE, "Starting database server");
      dbContainer.start();
      Runtime.getRuntime().addShutdownHook(new Thread(() -> {
        stopServer();
      }));
    }
    catch (final Exception e)
    {
      throw new SchemaCrawlerException("Could not start database server", e);
    }
  }

  public void stopServer()
  {
    if (dbContainer != null)
    {
      LOGGER.log(Level.FINE, "Stopping database server");
      dbContainer.stop();
      dbContainer = null;
    }
  }

  public String getPassword()
  {
    return dbContainer.getPassword();
  }

  public String getUser()
  {
    return dbContainer.getUsername();
  }

}
